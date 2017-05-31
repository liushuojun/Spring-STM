package com.dtr.oas.exception;

public class ComputerNotFoundException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2942873316407389036L;


    public ComputerNotFoundException() {
    }

    public ComputerNotFoundException(String message) {
        super(message);
    }

    public ComputerNotFoundException(Throwable cause) {
        super(cause);
    }

    public ComputerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComputerNotFoundException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}