package com.ub.core.user.service.exceptions;

public class UserExistException extends Exception {
    public UserExistException() {

    }

    public UserExistException(String message) {
        super(message);
    }
}
