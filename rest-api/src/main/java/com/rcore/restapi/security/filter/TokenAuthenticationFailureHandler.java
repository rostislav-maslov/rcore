package com.rcore.restapi.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcore.restapi.security.exceptions.*;
import com.rcore.restapi.web.api.response.ErrorApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class TokenAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof UserBlockedApiException)
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        else
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        if (exception instanceof UserNotExistApiException || exception instanceof TokenExpiredApiException || exception instanceof UserBlockedApiException || exception instanceof InvalidTokenFormatApiException) {
            ApiAuthenticationException e = (ApiAuthenticationException) exception;
            ErrorApiResponse error = ErrorApiResponse.of(e.getErrors());
            response.getWriter().write(objectMapper.writeValueAsString(error));
        }
    }
}
