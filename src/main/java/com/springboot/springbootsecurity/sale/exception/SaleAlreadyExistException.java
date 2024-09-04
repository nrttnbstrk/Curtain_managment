package com.springboot.springbootsecurity.sale.exception;

import java.io.Serial;

public class SaleAlreadyExistException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 6193758692340202558L;

    private static final String DEFAULT_MESSAGE = "";

    public SaleAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }

    public SaleAlreadyExistException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
