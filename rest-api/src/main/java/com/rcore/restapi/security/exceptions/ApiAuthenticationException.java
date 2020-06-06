package com.rcore.restapi.security.exceptions;

import com.rcore.restapi.exceptions.ExceptionDTO;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

import java.util.Arrays;
import java.util.List;

@Getter
public abstract class ApiAuthenticationException extends AuthenticationException {

    private List<ExceptionDTO> errors;

    public ApiAuthenticationException(String msg) {
        super(msg);
    }

    public ApiAuthenticationException(String title, String message, String domain, String details) {
        super(message);
        this.errors = Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title(title)
                        .message(message)
                        .build())
                .details(details)
                .domain(domain)
                .build());
    }

    public ApiAuthenticationException(String message, String domain, String details) {
        super(message);
        this.errors = Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .message(message)
                        .build())
                .details(details)
                .domain(domain)
                .build());
    }
}
