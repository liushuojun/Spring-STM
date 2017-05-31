package com.dtr.oas.exception;

public class DuplicateComputerException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7079003267052477038L;


    public DuplicateComputerException() {
    }

    public DuplicateComputerException(String arg0) {
        super(arg0);
    }

    public DuplicateComputerException(Throwable arg0) {
        super(arg0);
    }

    public DuplicateComputerException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public DuplicateComputerException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}
