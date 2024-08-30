package com.springboot.springbootsecurity.customer.exception;

import java.io.Serial;

public class CustomerAlreadyExistException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 6193758692340202558L;

    private static final String DEFAULT_MESSAGE = """
            Müşteri Zaten Mevcut!
            """;

    public CustomerAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }

    public CustomerAlreadyExistException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
