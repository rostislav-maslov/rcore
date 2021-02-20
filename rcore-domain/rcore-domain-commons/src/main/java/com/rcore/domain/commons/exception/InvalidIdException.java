package com.rcore.domain.commons.exception;

public class InvalidIdException extends DomainException {

    public InvalidIdException(String id) {
        super("Invalid Id: " + id);
    }
}
