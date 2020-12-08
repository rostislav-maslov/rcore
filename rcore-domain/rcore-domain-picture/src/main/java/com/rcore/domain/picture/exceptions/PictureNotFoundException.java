package com.rcore.domain.picture.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class PictureNotFoundException extends DomainException {

    public PictureNotFoundException(String id) {
        super("Picture not found by ID: " + id);
    }
}
