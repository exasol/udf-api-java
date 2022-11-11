package com.exasol;

/**
 * This exception indicates that the requested connection information could not be accessed, e.g. because the user has
 * insufficient privileges or the connection does not exist.
 */
public class ExaConnectionAccessException extends Exception {
    private static final long serialVersionUID = 1L;
    /** @serial Message without connection exception prefix. */
    private final String message;

    /**
     * Create a new instance of an {@link ExaConnectionAccessException}.
     *
     * @param message error message
     */
    public ExaConnectionAccessException(final String message) {
        super("connection exception: " + message);
        this.message = message;
    }

    /**
     * Get the error message.
     *
     * @return error message
     */
    @Override
    public String getMessage() {
        return message;
    }
}
