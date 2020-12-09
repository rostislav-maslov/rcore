package com.rcore.domain.picture.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class PictureNotBufferException extends DomainException {

    public PictureNotBufferException(String id) {
        super("Picture " + id + " not buffer");
    }
}
