package com.enb.curtainmanagement.customer.exception;

import java.io.Serial;

public class CustomerNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2329107146606835124L;



    public CustomerNotFoundException(final String message) {
        super(message);
    }

}
