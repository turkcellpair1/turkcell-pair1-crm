package com.turkcell.authserver.core.exceptions;

import java.util.NoSuchElementException;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
