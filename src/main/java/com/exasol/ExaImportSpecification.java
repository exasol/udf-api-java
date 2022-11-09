package com.exasol;

import java.util.List;
import java.util.Map;

/**
 * This class holds all information about a user-defined {@code IMPORT}, which can be started using the
 * {@code IMPORT FROM SCRIPT ...} statement.
 * <p>
 * To support a user defined {@code IMPORT} you can implement the callback method
 * {@code generateSqlForImportSpec(ExaImportSpecification importSpec)}. Please refer to the <a href=
 * "https://docs.exasol.com/db/latest/database_concepts/udf_scripts/java.htm?#UserDefinedImportCallbackFunction">Exasol
 * online guide</a> for more details.
 * </p>
 */
public interface ExaImportSpecification {
    /**
     * Check whether the {@code IMPORT} is part of a sub-select statement.
     *
     * @return {@code true}, if the import is used inside a {@code SELECT} statement (i.e. not inside an
     *         {@code IMPORT INTO} table statement)
     */
    public boolean isSubselect();

    /**
     * Get the names of the target columns in a sub-select scenario.
     * <p>
     * Use {@link ExaImportSpecification#isSubselect()} to check whether the import is part of a sub-select first.
     * </p>
     *
     * @return List of names of the specified target columns or empty list
     */
    public List<String> getSubselectColumnNames();

    /**
     * Get the data types of the target columns in a sub-select.
     * <p>
     * Use {@link ExaImportSpecification#isSubselect()} to check whether the import is part of a sub-select first.
     * </p>
     * <p>
     * The types are returned in SQL format (e.g. {@codeVARCHAR(100)}).
     * </p>
     *
     * @return List of names of the specified target columns or empty list
     */
    public List<String> getSubselectColumnSqlTypes();

    /**
     * Check whether the name of a connection is specified that the import should use.
     * <p>
     * If so, you can get the connection information by calling {@link ExaMetadata#getConnection(String)}.
     * </p>
     *
     * @return {@code true}, if the name of a connection was specified
     */
    public boolean hasConnectionName();

    /**
     * Get the name of the connection to be used for the import.
     * <p>
     * Use {@link ExaImportSpecification#hasConnectionName()} to find out if the connection was specified in the
     * {@code IMPORT} statement.
     * </p>
     *
     * @return name of the connection, if one was specified or otherwise {@code null}
     */
    public String getConnectionName();

    /**
     * Check whether the details of a connection are specified that the import should use.
     * <p>
     * If so, you can get the connection information by calling {@link ExaMetadata#getConnection(String)}.
     * </p>
     *
     * @return {@code true}, if connection information were provided.
     */
    public boolean hasConnectionInformation();

    /**
     * Get the connection details from the connection if one is specified for the import.
     *
     * @return connection information
     */
    public ExaConnectionInformation getConnectionInformation();

    /**
     * Get the parameters specified in the {@code IMPORT} statement.
     *
     * @return parameters specified in the {@code IMPORT} statement
     */
    public Map<String, String> getParameters();
}
