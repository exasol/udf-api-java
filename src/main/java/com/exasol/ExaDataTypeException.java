package com.exasol;

/**
 * This Exception indicates that a data type error occurred with the script input or output.
 */
public class ExaDataTypeException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Create a new instance of an {@link ExaDataTypeException}.
     */
    public ExaDataTypeException() {
        super();
    }

    /**
     * Create a new instance of an {@link ExaDataTypeException}.
     *
     * @param message error message
     */
    public ExaDataTypeException(String message) {
        super(message);
    }

    /**
     * Create a new instance of an {@link ExaDataTypeException}.
     *
     * @param message error message
     * @param cause exception causing this one
     */
    public ExaDataTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create a new instance of an {@link ExaDataTypeException}.
     *
     * @param cause exception causing this one
     */
    public ExaDataTypeException(Throwable cause) {
        super(cause);
    }
}
