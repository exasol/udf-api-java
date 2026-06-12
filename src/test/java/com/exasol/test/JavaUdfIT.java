package com.exasol.test;

import static com.exasol.matcher.ResultSetStructureMatcher.table;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.sql.*;
import java.util.Locale;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import com.exasol.dbbuilder.dialects.exasol.ExasolObjectConfiguration;
import com.exasol.dbbuilder.dialects.exasol.ExasolSchema;
import com.exasol.dbbuilder.dialects.exasol.udf.UdfScript;
import com.exasol.exasoltestsetup.ExasolTestSetup;
import com.exasol.exasoltestsetup.ExasolTestSetupFactory;
import com.exasol.udfdebugging.UdfTestSetup;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.exasol.bucketfs.Bucket;
import com.exasol.bucketfs.BucketAccessException;
import com.exasol.dbbuilder.dialects.exasol.ExasolObjectFactory;
import com.exasol.matcher.ResultSetStructureMatcher;
import com.exasol.mavenprojectversiongetter.MavenProjectVersionGetter;

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
    private static final ExasolTestSetup EXASOL = new ExasolTestSetupFactory().getTestSetup();
    private static final Logger LOGGER = Logger.getLogger(JavaUdfIT.class.getName());
    private static final String PROJECT_VERSION = MavenProjectVersionGetter.getCurrentProjectVersion();
    private static final String UDF_UNDER_TEST_JAR = "udf-api-java-" + PROJECT_VERSION + "-tests.jar";
    private static final Path UDF_UNDER_TEST_JAR_PATH = Path.of("target", UDF_UNDER_TEST_JAR);
    private static final String JAR_INCLUDE_DIRECTIVE = "%jar /buckets/bfsdefault/default/" + UDF_UNDER_TEST_JAR;

    private static Connection connection;
    private static UdfTestSetup udfTestSetup;
    private static ExasolSchema schema;

    @BeforeAll
    static void beforeAll() throws BucketAccessException, FileNotFoundException, SQLException {
        connection = EXASOL.createConnection();
        udfTestSetup=new UdfTestSetup(EXASOL, connection);
        final ExasolObjectFactory factory = new ExasolObjectFactory(connection,
                ExasolObjectConfiguration.builder().withJvmOptions(udfTestSetup.getJvmOptions()).build());
        schema = factory.createSchema("CONTEXT_SCHEMA");
        copyUdfUnderTestToDefaultBucket();
    }

    private static void copyUdfUnderTestToDefaultBucket() throws BucketAccessException, FileNotFoundException {
        final Bucket bucket = EXASOL.getDefaultBucket();
        LOGGER.info("Copying test UDF '" + UDF_UNDER_TEST_JAR_PATH + "' to '" + UDF_UNDER_TEST_JAR + "' in bucket '"
                + bucket + "'");
        try {
            bucket.uploadFile(UDF_UNDER_TEST_JAR_PATH, UDF_UNDER_TEST_JAR);
        } catch (final TimeoutException exception) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Got interrupted trying to install UDF under test.", exception);
        }
    }

    @AfterAll
    static void afterAll() throws SQLException {
        if ((connection != null) && !connection.isClosed()) {
            connection.close();
        }
        if (udfTestSetup != null) {
            udfTestSetup.close();
        }
    }

    @CsvSource({
            "getDatabaseName, DB1",
            "getDatabaseVersion, \\d+\\.\\d+\\.\\d+",
            "getNodeCount, 1",
            "getOutputType, RETURN",
            "getScopeUser, SYS",
            "getScriptCode, %jvmoption(?:\\R|.)*%jar(?:\\R|.)*class(?:\\R|.)*",
            "getScriptSchema, CONTEXT_SCHEMA",
            "getScriptName, CONTEXT_METHOD_GETSCRIPTNAME",
            "getScriptLanguage, Java \\d+\\.\\d+.\\d+" })
    @ParameterizedTest
    void testGetDatabaseContextInformation(final String methodName, final String expectedResult) {
        try(final UdfScript script = createContextMethodTestScript(methodName)) {
            final String value = executeScalarScriptWithStringReturn(script.getFullyQualifiedName(), methodName);
            assertThat("Result of method " + methodName + "()", value, matchesPattern(expectedResult));
        }
    }

    private UdfScript createContextMethodTestScript(final String methodName) {
        final String scriptName = "CONTEXT_METHOD_" + methodName.toUpperCase(Locale.ENGLISH);
        return schema.createUdfBuilder(scriptName)
                .parameter("method_name", "VARCHAR(100)")
                .inputType(UdfScript.InputType.SCALAR)
                .language(UdfScript.Language.JAVA)
                .content(JAR_INCLUDE_DIRECTIVE + ";\n%scriptclass com.exasol.test.testobject.MetadataMethodExerciser;")
                .returns("VARCHAR(2000)")
                .build();
    }

    private String executeScalarScriptWithStringReturn(final String fullyQualifiedScriptName, final String methodName) {
        try (final Statement statement = connection.createStatement();
                final ResultSet result = statement
                        .executeQuery("SELECT " + fullyQualifiedScriptName + "('" + methodName + "')")) {
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
        try(UdfScript script = schema.createUdfBuilder("GET_TIMESTAMP_SCRIPT")
                .parameter("V", "TIMESTAMP")
                .language(UdfScript.Language.JAVA)
                .inputType(UdfScript.InputType.SET)
                .content(JAR_INCLUDE_DIRECTIVE +";\n%scriptclass com.exasol.test.testobject.GetTimestampUdf;")
                .returns("VARCHAR(2000)")
                .build()) {
            assertQueryResult("SELECT " + script.getFullyQualifiedName() + "(T.V)" +
                            " FROM VALUES (TO_TIMESTAMP('" + date + "T" + time + "Z', 'YYYY-MM-DDTHH24:MI:SS.FF3Z')) AS T(V)",
                    table().row(date + " " + time));
        }
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
        try(final UdfScript script = schema.createUdfBuilder("SIZE_IN_SCALAR_CONTEXT")
                .language(UdfScript.Language.JAVA)
                .inputType(UdfScript.InputType.SCALAR)
                .content(JAR_INCLUDE_DIRECTIVE + ";\n%scriptclass com.exasol.test.testobject.GetSizeUdf;")
                .returns("INTEGER")
                .build()) {
            assertQueryResult("SELECT " + script.getFullyQualifiedName() + "()", table().row(1L));
        }
    }

    @Test
    void testGetSizeFromSetScript() {
        final String scriptName = "SIZE_IN_SET_CONTEXT";
        try(final UdfScript script = schema.createUdfBuilder(scriptName)
                .parameter("COL", "CHAR(1)")
                .language(UdfScript.Language.JAVA)
                .inputType(UdfScript.InputType.SET)
                .content(JAR_INCLUDE_DIRECTIVE + ";\n%scriptclass com.exasol.test.testobject.GetSizeUdf;")
                .returns("INTEGER")
                .build()
        ) {
            assertQueryResult("SELECT " + script.getFullyQualifiedName() + "(v) FROM VALUES ('a'), ('b'), ('c') AS v(v)",
                    table().row(3L));
        }
    }
}
