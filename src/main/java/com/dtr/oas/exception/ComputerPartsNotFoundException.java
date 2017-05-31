package com.dtr.oas.exception;

public class ComputerPartsNotFoundException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4097649488894179114L;

	public ComputerPartsNotFoundException() {
    }

    public ComputerPartsNotFoundException(String message) {
        super(message);
    }

    public ComputerPartsNotFoundException(Throwable cause) {
        super(cause);
    }

    public ComputerPartsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComputerPartsNotFoundException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}