package com.rcore.domain.file.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class FileNotFoundException extends DomainException {

    public FileNotFoundException(String id) {
        super("File not found by ID: " + id);
    }
}
