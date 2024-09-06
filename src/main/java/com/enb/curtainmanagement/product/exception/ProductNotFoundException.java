package com.enb.curtainmanagement.product.exception;

import java.io.Serial;

public class ProductNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2329107146606835124L;


    public ProductNotFoundException(final String message) {
        super(message);
    }

}
