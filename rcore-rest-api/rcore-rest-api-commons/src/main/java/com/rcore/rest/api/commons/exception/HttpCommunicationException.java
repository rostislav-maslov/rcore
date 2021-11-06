package com.rcore.rest.api.commons.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class HttpCommunicationException extends RuntimeException {
    //Ответ от сервера
    private final Object response;
}
