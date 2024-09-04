package com.springboot.springbootsecurity.subProduct.exception;

import java.io.Serial;

public class SubProductAlreadyExistException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 6193758692340202558L;



    public SubProductAlreadyExistException(final String message) {
        super(message);
    }

}
