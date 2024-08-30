package com.springboot.springbootsecurity.customer.exception;

import java.io.Serial;

public class CustomerNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2329107146606835124L;

    private static final String DEFAULT_MESSAGE = """
            Customer not found!
            """;

    public CustomerNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public CustomerNotFoundException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
