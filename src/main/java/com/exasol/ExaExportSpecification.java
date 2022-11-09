package com.exasol;

import java.util.List;
import java.util.Map;

/**
 * This class holds all information about a user-defined EXPORT, which can be started using the
 * {@code EXPORT INTO SCRIPT ...} statement.
 * <p>
 * To support a user-defined {@code EXPORT} you can implement the callback method
 * {@code generateSqlForExportSpec(ExaExportSpecification exportSpec)}. Please refer to the <a href=
 * "https://docs.exasol.com/db/latest/database_concepts/udf_scripts/java.htm#UserDefinedExportCallbackFunction">Exasol
 * online guide</a> for more details.
 * </p>
 */
public interface ExaExportSpecification {
    /**
     * Indicates whether the name of a connection was specified.
     * <p>
     * The script can then obtain the connection information via {@link ExaMetadata#getConnection(String)}.
     * </p>
     *
     * @return {@code true}, if the name of a connection was specified
     */
    public boolean hasConnectionName();

    /**
     * Get the name of the connection.
     * 
     * <p>
     * Use {@link ExaExportSpecification#hasConnectionName()} to check whether the name is set before calling this
     * method.
     * </p>
     * 
     * @return name of the connection, if one was specified or {@code null} otherwise
     */
    public String getConnectionName();

    /**
     * Check if the connection information was provided.
     * <p>
     * The script can then obtain the connection information via {@link ExaMetadata#getConnection(String)}.
     * </p>
     *
     * @return {@code true}, if connection information was provided
     */
    public boolean hasConnectionInformation();

    /**
     * Get the details of the connection information.
     *
     * @return connection information
     */
    public ExaConnectionInformation getConnectionInformation();

    /**
     * Get the export parameters
     * 
     * @return parameters specified in the {@code EXPORT} statement.
     */
    public Map<String, String> getParameters();

    /**
     * Check if the truncation option is set.
     *
     * @return {@code true}, if {@code TRUNCATE} was specified
     */
    public boolean hasTruncate();

    /**
     * Check if the replacement option is set.
     *
     * @return {@code true}, if {@code REPLACE} was specified
     */
    public boolean hasReplace();

    /**
     * This returns true if CREATED BY was specified in the EXPORT statement.
     *
     * @return {@code true}, if CREATED BY was specified
     */
    public boolean hasCreatedBy();

    /**
     * Get the definition for the target table creation.
     * <p>
     * {@code CREATED BY} defines a creation string which is used to create the table on the target system before the
     * export is started. The {@code CREATED BY} string contains a single SQL statement; multiple statements are not
     * supported.
     * </p>
     *
     * @return {@code CREATED BY} statement, if one was specified or otherwise {@code null}
     */
    public String getCreatedBy();

    /**
     * Get the names of all source columns
     *
     * @return names or columns in the {@code EXPORT} statement.
     */
    public List<String> getSourceColumnNames();

}
