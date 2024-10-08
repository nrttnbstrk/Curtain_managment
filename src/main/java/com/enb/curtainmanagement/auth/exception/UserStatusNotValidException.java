package com.enb.curtainmanagement.auth.exception;

import java.io.Serial;

public class UserStatusNotValidException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3440177088502218750L;

    private static final String DEFAULT_MESSAGE = """
            Kullanıcı statüsü geçerli değil!
            """;

    public UserStatusNotValidException() {
        super(DEFAULT_MESSAGE);
    }

    public UserStatusNotValidException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }

}
