package com.rcore.restapi.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BaseApiException extends Exception {

    private ExceptionDTO error;

    public BaseApiException(ExceptionDTO error) {
        this.error = error;
    }

    public BaseApiException(String title, String message, String domain, String details) {
        this.error = ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title(title)
                        .message(message)
                        .build())
                .details(details)
                .domain(domain)
                .build();
    }

    public BaseApiException(String message, String domain, String details) {
        this.error = ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .message(message)
                        .build())
                .details(details)
                .domain(domain)
                .build();
    }

}
