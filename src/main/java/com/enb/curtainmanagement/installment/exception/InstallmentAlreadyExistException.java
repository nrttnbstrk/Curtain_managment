package com.enb.curtainmanagement.installment.exception;

import java.io.Serial;

public class InstallmentAlreadyExistException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 6193758692340202558L;



    public InstallmentAlreadyExistException(final String message) {
        super(message);
    }

}
