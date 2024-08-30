package com.springboot.springbootsecurity.sale.exception;

import java.io.Serial;

public class SaleNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2329107146606835124L;

    private static final String DEFAULT_MESSAGE = """
            Customer not found!
            """;

    public SaleNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public SaleNotFoundException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
