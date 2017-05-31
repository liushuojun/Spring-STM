package com.dtr.oas.exception;

public class DuplicateCustomerException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3385656495346969128L;

	public DuplicateCustomerException() {
    }

    public DuplicateCustomerException(String arg0) {
        super(arg0);
    }

    public DuplicateCustomerException(Throwable arg0) {
        super(arg0);
    }

    public DuplicateCustomerException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public DuplicateCustomerException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}
