package com.rcore.domain.picture.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class InvalidPictureDataException extends DomainException {

    public InvalidPictureDataException() {
        super("Invalid picture data");
    }
}
