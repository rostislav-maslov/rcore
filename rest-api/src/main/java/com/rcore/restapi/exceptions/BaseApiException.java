package com.rcore.restapi.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@ToString
public class BaseApiException extends RuntimeException {

    private List<ExceptionDTO> errors = new ArrayList<>();

    public BaseApiException(List<ExceptionDTO> errors) {
        this.errors = errors;
    }

    public BaseApiException(ExceptionDTO error) {
        this.errors = Arrays.asList(error);
    }

    public BaseApiException(String title, String message, String domain, String details) {
        this.errors = Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title(title)
                        .message(message)
                        .build())
                .details(details)
                .domain(domain)
                .build());
    }

    public BaseApiException(String message, String domain, String details) {
        this.errors = Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .message(message)
                        .build())
                .details(details)
                .domain(domain)
                .build());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseApiException)) return false;
        BaseApiException that = (BaseApiException) o;
        return Objects.equals(errors, that.errors);
    }
}
