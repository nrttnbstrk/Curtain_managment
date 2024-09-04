package com.springboot.springbootsecurity.customer.exception;

import java.io.Serial;

public class CustomerAlreadyExistException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 6193758692340202558L;



    public CustomerAlreadyExistException(final String message) {
        super(message);
    }

}
