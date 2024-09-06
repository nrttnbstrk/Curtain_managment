package com.enb.curtainmanagement.product.exception;

import java.io.Serial;

public class ProductAlreadyExistException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 6193758692340202558L;



    public ProductAlreadyExistException(final String message) {
        super(message);
    }

}
