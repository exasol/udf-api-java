package com.exasol;

/**
 * This exception indicates an error during interaction with the ExaIterator interface.
 */
public class ExaIterationException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Create a new instance of an {@link ExaIterationException}.
     */
    public ExaIterationException() {
        super();
    }

    /**
     * Create a new instance of an {@link ExaIterationException}.
     *
     * @param message error message
     */
    public ExaIterationException(String message) {
        super(message);
    }

    /**
     * Create a new instance of an {@link ExaIterationException}.
     *
     * @param message error message
     * @param cause exception causing this one
     */
    public ExaIterationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create a new instance of an {@link ExaIterationException}.
     *
     * @param cause exception causing this one
     */
    public ExaIterationException(Throwable cause) {
        super(cause);
    }
}
