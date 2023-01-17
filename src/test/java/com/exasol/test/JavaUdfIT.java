package com.exasol.test;

import com.exasol.bucketfs.Bucket;
import com.exasol.bucketfs.BucketAccessException;
import com.exasol.containers.ExasolContainer;
import com.exasol.dbbuilder.dialects.Schema;
import com.exasol.dbbuilder.dialects.exasol.ExasolObjectFactory;
import com.exasol.matcher.ResultSetStructureMatcher;
import com.exasol.mavenprojectversiongetter.MavenProjectVersionGetter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import static com.exasol.matcher.ResultSetStructureMatcher.table;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * This is an integration test that uses a JAR built from classes under test with the API that is the main test subject.
 * <p>
 * We intentionally don't use inline-Java in the UDF-scripts, since that would build the UDF code against the API
 * shipped with the language container. But this is exactly <i>not</i> what we want to build against. Imagine we forget
 * an API method in the current version. It's still present in the version shipped with the LC, so the compiler will not
 * warn us.
 * </p>
 * <p>
 * Using an extra JAR in the integration test has many unpleasant consequences. One is that the test cases are tightly
 * coupled to the probe code in the test JAR. Another one is that you have to rebuild the test JAR if you touched the
 * probe code before you run the test. If you run {@code mvn clean:verify}, this is ensured. But if you run tests
 * straight from your IDE, the test JAR is not automatically built. So be careful.
 * </p>
 */
@Testcontainers
class JavaUdfIT {
    @Container
    private static final ExasolContainer<? extends ExasolContainer<?>> EXASOL;
    static {
        try(final ExasolContainer<? extends ExasolContainer<?>> container = new ExasolContainer<>()) {
            EXASOL = container.withReuse(true);
        }
    }
    private static final Logger LOGGER = Logger.getLogger(JavaUdfIT.class.getName());
    private static final String PROJECT_VERSION = MavenProjectVersionGetter.getCurrentProjectVersion();
    private static final String UDF_UNDER_TEST_JAR = "udf-api-java-" + PROJECT_VERSION + "-tests.jar";
    private static final Path UDF_UNDER_TEST_JAR_PATH = Path.of("target", UDF_UNDER_TEST_JAR);
    private static final String JAR_INCLUDE_DIRECTIVE = "%jar /buckets/bfsdefault/default/" + UDF_UNDER_TEST_JAR;

    private static Connection connection;
    private static Schema schema;

    @BeforeAll
    static void beforeAll() throws SQLException, BucketAccessException, FileNotFoundException {
        connection = EXASOL.createConnection();
        final ExasolObjectFactory factory = new ExasolObjectFactory(connection);
        schema = factory.createSchema("CONTEXT_SCHEMA");
        copyUdfUnderTestToDefaultBucket();
    }

    private static void copyUdfUnderTestToDefaultBucket()
            throws BucketAccessException, FileNotFoundException {
        final Bucket bucket = EXASOL.getDefaultBucket();
        LOGGER.info("Copying test UDF '" + UDF_UNDER_TEST_JAR_PATH + "' to '" + UDF_UNDER_TEST_JAR + "' in bucket '"
                + bucket + "'");
        try {
            bucket.uploadFile(UDF_UNDER_TEST_JAR_PATH, UDF_UNDER_TEST_JAR);
        } catch (TimeoutException exception) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Got interrupted trying to install UDF under test.", exception);
        }
    }

    @AfterAll
    static void afterAll() throws SQLException {
        if ((connection != null) && !connection.isClosed()) {
            connection.close();
        }
    }

    @CsvSource({ //
            "getDatabaseName, DB1", //
            "getDatabaseVersion, 7\\.1\\.15", //
            "getNodeCount, 1", //
            "getOutputType, RETURN", //
            "getScopeUser, SYS", //
            "getScriptCode, %jar(?:\\R|.)*class(?:\\R|.)*", //
            "getScriptSchema, CONTEXT_SCHEMA", //
            "getScriptName, CONTEXT_METHOD_GETSCRIPTNAME", //
            "getScriptLanguage, Java \\d+\\.\\d+.\\d+" })
    @ParameterizedTest
    void testGetDatabaseContextInformation(final String methodName, final String expectedResult) {
        final String fullyQualifiedScriptName = createContextMethodTestScript(schema, methodName);
        final String value = executeScalarScriptWithStringReturn(fullyQualifiedScriptName, methodName);
        assertThat(value, matchesPattern(expectedResult));
    }

    private String createContextMethodTestScript(final Schema schema, final String methodName) {
        final String scriptName = "CONTEXT_METHOD_" + methodName.toUpperCase();
        final String fullyQualifiedScriptName = getFullyQualifiedScriptName(schema, scriptName);
        executeStatement("CREATE JAVA SCALAR SCRIPT " + fullyQualifiedScriptName //
                + "(method_name VARCHAR(100)) RETURNS VARCHAR(2000) AS\n" //
                + "    " + JAR_INCLUDE_DIRECTIVE + ";\n" //
                + "    %scriptclass com.exasol.test.testobject.MetadataMethodExerciser;\n" //
                + "\n/\n");
        return fullyQualifiedScriptName;
    }

    private static String getFullyQualifiedScriptName(final Schema schema, final String scriptName) {
        return "\"" + schema.getName() + "\".\"" + scriptName + "\"";
    }

    private static void executeStatement(final String sql) {
        try (final Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (final SQLException exception) {
            throw new AssertionError("Unable to execute statement:\n" + sql + "\n", exception);
        }
    }

    private String executeScalarScriptWithStringReturn(final String fullyQualifiedScriptName, final String methodName) {
        try (final Statement statement = connection.createStatement();
                final ResultSet result = statement.executeQuery("SELECT " + fullyQualifiedScriptName + "('" +
                        methodName + "')")) {
            result.next();
            return result.getString(1);
        } catch (final SQLException exception) {
            throw new AssertionError("Unable to create test script " + fullyQualifiedScriptName, exception);
        }
    }

    @Test
    void testGetTimestampFromSetScript() {
        final String date = "2001-02-03";
        final String time = "04:05:06.007";
        final String scriptName = "GET_TIMESTAMP_SCRIPT";
        final String fullyQualifiedScriptName = getFullyQualifiedScriptName(schema, scriptName);
        executeStatement("CREATE JAVA SET SCRIPT " + fullyQualifiedScriptName //
                + "(V TIMESTAMP) RETURNS VARCHAR(2000) AS\n" //
                + "    " + JAR_INCLUDE_DIRECTIVE + ";\n" //
                + "    %scriptclass com.exasol.test.testobject.GetTimestampUdf;\n" //
                + "/\n\n");
        assertQueryResult("SELECT " + fullyQualifiedScriptName + "(T.V)" + //
                "FROM VALUES (TO_TIMESTAMP('" + date + "T" + time + "Z', 'YYYY-MM-DDTHH24:MI:SS.FF3Z')) AS T(V)", //
                table().row(date + " " + time));
    }

    private static void assertQueryResult(final String sql, final ResultSetStructureMatcher.Builder rowMatcher) {
        try (final Statement statement = connection.createStatement();
             final ResultSet result = statement.executeQuery(sql)) {
            assertThat(result, rowMatcher.matches());
        } catch (final SQLException exception) {
            throw new AssertionError("Unable to assert result of statement: " + sql, exception);
        }
    }

    @Test
    void testGetSizeFromScalarScript() {
        final String scriptName = "SIZE_IN_SCALAR_CONTEXT";
        final String fullyQualifiedScriptName = getFullyQualifiedScriptName(schema, scriptName);
        executeStatement("CREATE JAVA SCALAR SCRIPT " + fullyQualifiedScriptName + "() RETURNS INTEGER AS\n" //
                + "    " + JAR_INCLUDE_DIRECTIVE + ";\n" //
                + "    %scriptclass com.exasol.test.testobject.GetSizeUdf;\n" //
                + "/\n\n");
        assertQueryResult("SELECT "+ fullyQualifiedScriptName + "()", table().row(1L));
    }
    @Test
    void testGetSizeFromSetScript() {
        final String scriptName = "SIZE_IN_SET_CONTEXT";
        final String fullyQualifiedScriptName = getFullyQualifiedScriptName(schema, scriptName);
        executeStatement("CREATE JAVA SET SCRIPT " + fullyQualifiedScriptName + "(COL CHAR(1)) RETURNS INTEGER AS\n" //
                + "    " + JAR_INCLUDE_DIRECTIVE + ";\n" //
                + "    %scriptclass com.exasol.test.testobject.GetSizeUdf;\n" //
                + "/\n\n");
        assertQueryResult("SELECT "+ fullyQualifiedScriptName + "(v) FROM VALUES ('a'), ('b'), ('c') AS v(v)", //
                table().row(3L));
    }
}