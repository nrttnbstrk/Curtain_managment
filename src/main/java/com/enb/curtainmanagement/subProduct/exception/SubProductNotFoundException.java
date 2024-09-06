package com.enb.curtainmanagement.subProduct.exception;

import java.io.Serial;

public class SubProductNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2329107146606835124L;



    public SubProductNotFoundException(final String message) {
        super(message);
    }

}
