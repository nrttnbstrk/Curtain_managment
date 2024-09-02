package com.springboot.springbootsecurity.subProduct.exception;

import java.io.Serial;

public class SubProductNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2329107146606835124L;

    private static final String DEFAULT_MESSAGE = """
            URUN BULUNAMADI""";

    public SubProductNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public SubProductNotFoundException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
