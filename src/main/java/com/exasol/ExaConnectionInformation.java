package com.exasol;

/**
 * This class holds all information about a specific connection, as it can be created using the {@code CREATE
 * CONNECTION} statement.
 */
public interface ExaConnectionInformation {
    /**
     * Type of database connection
     */
    public enum ConnectionType {
        /** Connection authenticated by username and password */
        PASSWORD
    }

    /**
     * Get the connection type.
     * 
     * @return type of the connection
     */
    public ConnectionType getType();

    /**
     * Get the target address of the connections
     * 
     * @return address of the connection, i.e. the part that follows the {@code TO} keyword in the {@code CREATE
     * CONNECTION} command
     */
    public String getAddress();

    /**
     * Get the username.
     * 
     * @return username of the connection, i.e. the part that follows the {@code USER} keyword in the {@code CREATE
     * CONNECTION} command
     */
    public String getUser();

    /**
     * Get the connection password.
     * 
     * @return password of the connection, i.e. the part that follows the {@code IDENTIFIED BY} keyword in the {@code
     * CREATE CONNECTION command
     */
    public String getPassword();
}
