package com.springboot.springbootsecurity.admin.exception;

import java.io.Serial;

public class AdminAlreadyExistException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -8596955790221338007L;


    public AdminAlreadyExistException(final String message) {
        super(message);
    }

}
