import com.exasol.containers.ExasolContainer;
import com.exasol.dbbuilder.dialects.Schema;
import com.exasol.dbbuilder.dialects.exasol.ExasolObjectFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Testcontainers
class ExaMetadataIT {
    @Container
    private static final ExasolContainer<? extends ExasolContainer<?>> EXASOL = instantiateContainer();

    private static ExasolContainer<? extends ExasolContainer<?>> instantiateContainer() {
        try (ExasolContainer<? extends ExasolContainer<?>> container = new ExasolContainer<>()) {
            return container.withReuse(true);
        }
    }

    private static Schema schema;

    @BeforeAll
    static void beforeAll() throws SQLException {
        final ExasolObjectFactory factory = new ExasolObjectFactory(EXASOL.createConnection());
        schema = factory.createSchema("CONTEXT_SCHEMA");
    }

    @CsvSource({ //
            "getDatabaseName, DB1", //
            "getDatabaseVersion, 7\\.1\\.15", //
            "getNodeCount, 1", //
            "getOutputType, RETURN", //
            "getScopeUser, SYS", //
            "getScriptCode, class(?:\\R|.)*", //
            "getScriptSchema, CONTEXT_SCHEMA", //
            "getScriptName, CONTEXT_METHOD_GETSCRIPTNAME", //
            "getScriptLanguage, Java \\d+\\.\\d+.\\d+" })
    @ParameterizedTest
    void testGetDatabaseContextInformation(final String methodName, final String expectedResult) {
        final String fullyQualifiedScriptName = createContextMethodTestScript(schema, methodName);
        final String value = executeScalarScriptWithStringReturn(fullyQualifiedScriptName);
        assertThat(value, matchesPattern(expectedResult));
    }

    private String createContextMethodTestScript(final Schema schema, final String methodName) {
        final String scriptName = "CONTEXT_METHOD_" + methodName.toUpperCase();
        final String fullyQualifiedScriptName = "\"" + schema.getName() + "\".\"" + scriptName + "\"";
        final String content = "class " + scriptName + " {\n" //
                + "    static String run(final ExaMetadata metadata, final ExaIterator context) throws Exception {\n" //
                + "        return \"\" + metadata." + methodName + "();\n" // force string conversion
                + "    }\n" //
                + "}\n";
        try (final Connection connection = EXASOL.createConnection();
                final Statement statement = connection.createStatement()) {
            statement.execute("CREATE JAVA SCALAR SCRIPT " + fullyQualifiedScriptName + "() RETURNS VARCHAR(2000) AS\n"
                    + content + "\n/\n");
            return fullyQualifiedScriptName;
        } catch (SQLException exception) {
            throw new AssertionError("Unable to create test script " + fullyQualifiedScriptName, exception);
        }
    }

    private String executeScalarScriptWithStringReturn(final String fullyQualifiedScriptName) {
        try (final Connection connection = EXASOL.createConnection();
                final Statement statement = connection.createStatement();
                final ResultSet result = statement.executeQuery("SELECT " + fullyQualifiedScriptName + "()")) {
            result.next();
            return result.getString(1);
        } catch (SQLException exception) {
            throw new AssertionError("Unable to create test script " + fullyQualifiedScriptName, exception);
        }
    }
}
