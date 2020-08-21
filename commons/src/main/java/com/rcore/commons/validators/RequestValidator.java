package com.rcore.commons.validators;

public interface RequestValidator<Request> {

    void validate(Request request);

}
