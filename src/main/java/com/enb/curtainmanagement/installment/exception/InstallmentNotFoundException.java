package com.enb.curtainmanagement.installment.exception;

import java.io.Serial;

public class InstallmentNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2329107146606835124L;



    public InstallmentNotFoundException(final String message) {
        super(message);
    }

}
