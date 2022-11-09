package com.exasol;

/**
 * This exception indicates that the compilation of a script failed, or that the script API callback functions are not
 * correctly implemented.
 */
public class ExaCompilationException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Create a new instance of an {@link ExaCompilationException}.
     */
    public ExaCompilationException() {
        super();
    }

    /**
     * Create a new instance of an {@link ExaCompilationException}.
     *
     * @param message error message
     */
    public ExaCompilationException(String message) {
        super(message);
    }

    /**
     * Create a new instance of an {@link ExaCompilationException}.
     *
     * @param message error message
     * @param cause exception causing this one
     */
    public ExaCompilationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create a new instance of an {@link ExaCompilationException}.
     *
     * @param cause exception causing this one
     */
    public ExaCompilationException(Throwable cause) {
        super(cause);
    }
}
