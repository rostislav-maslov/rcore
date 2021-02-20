package com.rcore.domain.file.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class InvalidFileDataException extends DomainException {

    public InvalidFileDataException() {
        super("Invalid file data");
    }
}
