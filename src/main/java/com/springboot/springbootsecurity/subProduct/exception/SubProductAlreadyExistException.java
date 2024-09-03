package com.springboot.springbootsecurity.subProduct.exception;

import java.io.Serial;

public class SubProductAlreadyExistException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 6193758692340202558L;

    private static final String DEFAULT_MESSAGE = """
            ALT ÜRÜN Zaten Mevcut!
            """;

    public SubProductAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }

    public SubProductAlreadyExistException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
