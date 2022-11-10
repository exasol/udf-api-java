package com.exasol;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * This interface enables UDF scripts to iterate over input data and to emit output.
 * <p>
 * Many of the interface functions in this class behave differently depending on whether they are used in the context of
 * a "SCALAR" or a "SET" UDF script. Please refer to the section
 * <a href="https://docs.exasol.com/db/latest/database_concepts/udf_scripts.htm">"UDF Scripts" in the Exasol online
 * guide</a> for an explanation of the two different types.
 * </p>
 */
public interface ExaIterator {
    /**
     * Get the number of input rows of the script.
     * <p>
     * If this is a "SET" UDF script, this method returns the number of rows for the current group.
     * </p>
     * <p>
     * If this is a "SCALAR" UDF script, it will return the total number of rows to be processed by this JVM instance.
     * </p>
     *
     * @return number of input rows for this script.
     */
    public long size() throws ExaIterationException;

    /**
     * Move the iterator to the next row of the current group, while there are still rows left.
     * <p>
     * This method only applies in the context of "SET" UDF scripts.
     * </p>
     * <p>
     * Initially, the iterator points to the first row, so call this method after processing a row.
     * </p>
     * The following code can be used to process all rows of a group: <blockquote>
     * 
     * <pre>
     * public static void run(ExaMetadata meta, ExaIterator iter) throws Exception {
     *     do {
     *         // access data here, e.g. with iter.getString("MY_COLUMN");
     *     } while (iter.next());
     * }
     * </pre>
     * 
     * </blockquote>
     *
     * @return {@code true}, if there is a next row and the iterator was increased to it, {@code false}, if there is no more row for
     *         this group
     */
    public boolean next() throws ExaIterationException;

    /**
     * Resets the iterator to the first input row.
     * <p>
     * This is only allowed in the context of "SET" UDF scripts.
     * </p>
     */
    public void reset() throws ExaIterationException;

    /**
     * Emit an output row.
     * <p>
     * This is only allowed for "SET" UDF scripts.
     * </p>
     * <p>
     * Note that you can emit using multiple function arguments or an object array:
     * </p>
     * <blockquote>
     * 
     * <pre>
     * iter.emit(1, "a");
     * iter.emit(new Object[] { 1, "a" });
     * </pre>
     * 
     * </blockquote>
     */
    public void emit(Object... values) throws ExaIterationException, ExaDataTypeException;

    /**
     * Get the value of the column at the given index as {@link Integer}.
     * <p>
     * This can be used for the SQL data type {@code DECIMAL(p, 0)}.
     * </p>
     *
     * @param column index of the column, starting at 0
     *
     * @return value of the specified column of the current row as an {@link Integer} object or {@code null} if the column value is {@code NULL}
     */
    public Integer getInteger(int column) throws ExaIterationException, ExaDataTypeException;

    /**
     * Get the value of the column with the given name as {@link Integer}.
     * <p>
     * This can be used for the SQL data type {@code DECIMAL(p, 0)}.
     * </p>
     *
     * @param name name of the column
     *
     * @return value of the specified column of the current row as an {@link Integer} object or {@code null} if the column value is {@code NULL}
     */
    public Integer getInteger(String name) throws ExaIterationException, ExaDataTypeException;

    /**
     * Get the value of the column at the given index as {@link Long}.
     * <p>
     * This can be used for the SQL data type {@code DECIMAL(p, 0)}.
     * </p>
     *
     * @param column index of the column, starting at 0
     *
     * @return value of the specified column of the current row as a {@link Long} object or {@code null} if the column value is {@code NULL}
     */
    public Long getLong(int column) throws ExaIterationException, ExaDataTypeException;

    /**
     * Get the value of the column at the given index as {@link Long}.
     * <p>
     * This can be used for the SQL data type {@code DECIMAL(p, 0)}.
     * </p>
     *
     * @param name name of the column
     *
     * @return value of the specified column of the current row as a {@link Long} object or {@code null} if the column value is {@code NULL}
     */
    public Long getLong(String name) throws ExaIterationException, ExaDataTypeException;

    /**
     * Get the value of the column at the given index as {@link BigDecimal}.
     * <p>
     * This can be used for the SQL data types {@code DECIMAL(p, 0)} and {@code DECIMAL(p, s)}.
     * </p>
     *
     * @param column index of the column, starting with 0
     *
     * @return value of the specified column of the current row as a {@link BigDecimal} object or {@code null} if the column value is {@code NULL}
     */
    public BigDecimal getBigDecimal(int column) throws ExaIterationException, ExaDataTypeException;

    /**
     * Get the value of the column with the given name as {@link BigDecimal}.
     * <p>
     * This can be used for the SQL data types {@code DECIMAL(p, 0)} and {@code DECIMAL(p, s)}.
     * </p>
     *
     * @param name name of the column
     *
     * @return value of the specified column of the current row as a {@link BigDecimal} object or {@code null} if the column value is {@code NULL}
     */
    public BigDecimal getBigDecimal(String name) throws ExaIterationException, ExaDataTypeException;

    /**
     * Get the value of the column at the given index as {@link Double}.
     * <p>
     * This can be used for the SQL data type {@code DOUBLE}.
     * </p>
     * 
     * @param column index of the column, starting with 0
     *
     * @return value of the specified column of the current row as a {@link Double} object or {@code null} if the column value is {@code NULL}
     */
    public Double getDouble(int column) throws ExaIterationException, ExaDataTypeException;

    /**
     * Get the value of the column with the given name as {@link Double}.
     * <p>
     * This can be used for the SQL data type {@code DOUBLE}.
     * </p>
     *
     * @param name name of the column
     *
     * @return value of the specified column of the current row as a {@link Double} object or {@code null} if the column value is {@code NULL}
     */
    public Double getDouble(String name) throws ExaIterationException, ExaDataTypeException;

    /**
     * Get the value of the column at the given index as {@link String}.
     * <p>
     * This can be used for the SQL data type {@code VARCHAR} and {@code CHAR}.
     * </p>
     *
     * @param column index of the column, starting with 0
     *
     * @return value of the specified column of the current row as a {@link String} object or {@code null} if the column value is {@code NULL}
     */
    public String getString(int column) throws ExaIterationException, ExaDataTypeException;

    /**
     * Get the value of the column with the given name as {@link String}.
     * <p>
     * This can be used for the SQL data type {@code VARCHAR} and {@code CHAR}.
     * </p>
     *
     * @param name name of the column
     *
     * @return value of the specified column of the current row as a {@link String} object or {@code null} if the column value is {@code NULL}
     */
    public String getString(String name) throws ExaIterationException, ExaDataTypeException;

    /**
     * Get the value of the column at the given index as {@link Boolean}.
     * <p>
     * This can be used for the SQL data type {@code BOOLEAN}.
     * </p>
     *
     * @param column index of the column, starting with 0
     *
     * @return value of the specified column of the current row as a {@link Boolean} object or {@code null} if the column value is {@code NULL}
     */
    public Boolean getBoolean(int column) throws ExaIterationException, ExaDataTypeException;

    /**
     * Get the value of the column with the given name as {@link Boolean}.
     * <p>
     * This can be used for the SQL data type {@code BOOLEAN}.
     * </p>
     *
     * @param name name of the column
     *
     * @return value of the specified column of the current row as a {@link Boolean} object or {@code null} if the column value is {@code NULL}
     */
    public Boolean getBoolean(String name) throws ExaIterationException, ExaDataTypeException;

    /**
     * Get the value of the column at the given index as {@link Date}.
     * <p>
     * This can be used for the SQL data type {@code DATE}.
     * </p>
     *
     * @param column index of the column, starting with 0
     *
     * @return value of the specified column of the current row as a {@link Date} object or {@code null} if the column value is {@code NULL}
     */
    public Date getDate(int column) throws ExaIterationException, ExaDataTypeException;

    /**
     * Get the value of the column with the given name as {@link Date}.
     * <p>
     * This can be used for the SQL data type {@code DATE}.
     * </p>
     *
     * @param name name of the column
     *
     * @return value of the specified column of the current row as a {@link Date} object or {@code null} if the column value is {@code NULL}
     */
    public Date getDate(String name) throws ExaIterationException, ExaDataTypeException;

    /**
     * Get the value of the column with the given name as {@link Timestamp}.
     * <p>
     * This can be used for the SQL data type {@code TIMESTAMP}.
     * </p>
     *
     * @param name name of the column
     * 
     * @return value of the specified column of the current row as a {@link Timestamp} object or {@code null} if the column value is {@code NULL}
     */
    public Timestamp getTimestamp(String name) throws ExaIterationException, ExaDataTypeException;

    /**
     * Get the value of the column at the given index as {@link Object}.
     * <p>
     * This can be used for all SQL data types. You have to cast the value appropriately.
     * </p>
     *
     * @param column index of the column, starting with 0
     *
     * @return value of the specified column of the current row as a {@link Object} object or {@code null} if the column value is {@code NULL}
     */
    public Object getObject(int column) throws ExaIterationException, ExaDataTypeException;

    /**
     * Get the value of the column with the given name as {@link Object}.
     * <p>
     * This can be used for all SQL data types. You have to cast the value appropriately.
     * </p>
     *
     * @param name name of the column
     *
     * @return value of the specified column of the current row as a {@link Object} object or {@code null} if the column value is {@code NULL}
     */
    public Object getObject(String name) throws ExaIterationException, ExaDataTypeException;
}
