package com.exasol;

import java.math.BigInteger;

/**
 * This interface enables scripts to access metadata such as information about the database and the script.
 * <p>
 * Furthermore, it provides related methods, e.g. to access connections and to import scripts.
 * </p>
 */
public interface ExaMetadata {
    /**
     * Get the name of the database the script runs on.
     * 
     * @return name of the database
     */
    public String getDatabaseName();

    /**
     * Get the version of the database the script runs on.
     * 
     * @return version of the database
     */
    public String getDatabaseVersion();

    /**
     * Get the name and version of the execution environment (aka. "script language") the script runs on.
     * 
     * @return name and version of the script language, e.g. "Java 11"
     */
    public String getScriptLanguage();

    /**
     * Get the name of the database object as which the script is registered in the database.
     * 
     * @return name of the script
     */
    public String getScriptName();

    /**
     * Get the name of the database schema that is the parent database object of the script.
     * 
     * @return name of the script schema
     */
    public String getScriptSchema();

    /**
     * Get the name of the database user that was used to run the script.
     * 
     * @return name of the script user
     */
    public String getCurrentUser();

    /**
     * Get the name of the database user who is the owner of the script.
     * 
     * @return name of the script scope user
     */
    public String getScopeUser();

    /**
     * If the script is executed in the context of a selected (aka. "open") schema, get the name of that schema.
     * 
     * @return name of the current open schema or {@code null} if no schema is selected
     */
    public String getCurrentSchema();

    /**
     * Get the content of the script.
     * 
     * @return text of the script
     */
    public String getScriptCode();

    /**
     * Get the database session this script runs in.
     * 
     * @return ID of the session in which the current statement is executed
     */
    public String getSessionId();

    /**
     * Get the ID of the statement the script runs in.
     * 
     * @return ID of the current statement
     */
    public long getStatementId();

    /**
     * Get the number of data nodes of the database cluster running the script.
     * 
     * @return number of nodes in the cluster
     */
    public long getNodeCount();

    /**
     * Get ID of the cluster node the script runs on.
     * 
     * @return ID of the node on which the current JVM is executed, starting with 0
     */
    public long getNodeId();

    /**
     * Get unique number that identifies the local Java VM.
     * <p>
     * The IDs of the virtual machines have no relation to each other.
     * </p>
     * 
     * @return unique ID of the local JVM
     */
    public String getVmId();

    /**
     * Get the memory limit for the JVM process that powers the script.
     * <p>
     * If this memory is exceeded, the database resource management will kill the JVM process.
     * </p>
     * 
     * @return memory limit for the current JVM process in bytes.
     */
    public BigInteger getMemoryLimit();

    /**
     * Get the type of the UDF script.
     * 
     * @return input type of the script, either {@code SCALAR} or {@code SET}
     */
    public String getInputType();

    /**
     * Get the number of input columns.
     * 
     * @return number of input columns
     */
    public long getInputColumnCount();

    /**
     * Get the name of the input column at the given index.
     * 
     * @param column index of the column, starting with 0
     * 
     * @return name of the specified input column
     */
    public String getInputColumnName(int column) throws ExaIterationException;

    /**
     * Get the Java data type of the input column at the given index.
     * <p>
     * If this method for example returns {@code java.lang.String}, you can access the data from this column using
     * {@link ExaIterator#getString(int)}.
     * </p>
     * 
     * @param column index of the column, starting with 0
     * 
     * @return Java class used to represent data from the specified column
     */
    public Class<?> getInputColumnType(int column) throws ExaIterationException;

    /**
     * Get the SQL data type of the input column at the given index.
     * <p>
     * This is a string in SQL syntax, e.g. {@code DECIMAL(18,0)}.
     * </p>
     *
     * @param column index of the column, starting with 0
     *
     * @return sql type of the specified column
     */
    public String getInputColumnSqlType(int column) throws ExaIterationException;

    /**
     * Get the precision of the input column at the given index.
     *
     * @param column index of the column, starting with 0
     *
     * @return data type precision of the specified column, e.g. the precision of a {@code DECIMAL} data type
     */
    public long getInputColumnPrecision(int column) throws ExaIterationException;

    /**
     * Get the scale of the input column at the given index.
     *
     * @param column index of the column, starting with 0
     *
     * @return data type scale of the specified column, e.g. the scale of a {@code DECIMAL} data type
     */
    public long getInputColumnScale(int column) throws ExaIterationException;

    /**
     * Length of the input column at the given index.
     *
     * @param column index of the column, starting with 0
     *
     * @return data type length of the specified column, e.g. the length of a {@code VARCHAR} data type.
     */
    public long getInputColumnLength(int column) throws ExaIterationException;

    /**
     * Get the type of output the script produces.
     *
     * @return output type of the script, either {@code RETURNS} or {@code EMITS}
     */
    public String getOutputType();

    /**
     * Get the number of output columns.
     *
     * @return number of output columns
     */
    public long getOutputColumnCount();

    /**
     * Get the name of the output column at the given index.
     *
     * @param column index of the column, starting with 0
     *
     * @return name of the specified output column
     */
    public String getOutputColumnName(int column) throws ExaIterationException;

    /**
     * Get the Java data type of the output column at the given index.
     * <p>
     * If this returns {@code java.lang.String}, you can emit data for this column using a {@link String}.
     * </p>
     *
     * @param column index of the column, starting with 0
     *
     * @return the java class used to represent data from the specified output column
     */
    public Class<?> getOutputColumnType(int column) throws ExaIterationException;

    /**
     * Get the SQL data type of the output column at the given index.
     * <p>
     * This is a string in SQL syntax, e.g. {@code DECIMAL(18,0)}.
     * </p>
     *
     * @param column index of the column, starting with 0
     *
     * @return sql type of the specified output column
     */
    public String getOutputColumnSqlType(int column) throws ExaIterationException;

    /**
     * Get the precision of the output column at the given index.
     *
     * @param column index of the column, starting with 0
     *
     * @return data type precision of the specified output column, e.g. the precision of a {@code DECIMAL} data type
     */
    public long getOutputColumnPrecision(int column) throws ExaIterationException;

    /**
     * Get the scale of the column at the given index.
     *
     * @param column index of the column, starting with 0
     *
     * @return data type scale of the specified output column, e.g. the scale of a {@code DECIMAL} data type
     */
    public long getOutputColumnScale(int column) throws ExaIterationException;

    /**
     * Get the length of the output column at the given index.
     *
     * @param column index of the column, starting with 0
     *
     * @return data type length of the specified column, e.g. the length of a {@code VARCHAR} data type
     */
    public long getOutputColumnLength(int column) throws ExaIterationException;

    /**
     * Dynamically loads the code from the specified script, compiles it, and returns an instance of the main script
     * class.
     * <p>
     * You can use Java Reflection to work with this class.
     * </p>
     * <p>
     * Please note that there is a simple way to include other code using the keywords {@code %import} and {@code %jar}
     * in the script code (see user manual).
     * </p>
     * 
     * @param name The name of the script to be imported (case-sensitive)
     *
     * @return instance of the main script class of the imported script
     */
    public Class<?> importScript(String name) throws ExaCompilationException, ClassNotFoundException;

    /**
     * Get the details for a connection object with the given name.
     * <p>
     * Access the information of a connection (created with
     * <a href="https://docs.exasol.com/db/latest/sql/create_connection.htm">{@code CREATE CONNECTION}</a>). The
     * executing user must have the according privileges to access the script (see user manual).
     * </p>
     *
     * @param name name of the connection
     * @return an ExaConnectionInformation instance holding all information of the connection
     */
    public ExaConnectionInformation getConnection(String name) throws ExaConnectionAccessException;

}
