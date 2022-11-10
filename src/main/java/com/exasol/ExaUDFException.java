package com.exasol;

/**
 * This exception indicates that an exception during the execution of user code happened.
 */
public class ExaUDFException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Create a new instance of an {@link ExaUDFException}.
     */
    public ExaUDFException() {
        super();
    }

    /**
     * Create a new instance of an {@link ExaUDFException}.
     *
     * @param message error message
     */
    public ExaUDFException(String message) {
        super(message);
    }

    /**
     * Create a new instance of an {@link ExaUDFException}.
     *
     * @param message error message
     * @param cause exception causing this one
     */
    public ExaUDFException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create a new instance of an {@link ExaUDFException}.
     *
     * @param cause exception causing this one
     */
    public ExaUDFException(Throwable cause) {
        super(cause);
    }
}
