package com.rcore.rest.api.spring.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcore.domain.security.exceptions.ParsingTokenException;
import com.rcore.rest.api.commons.response.ErrorApiResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletRequest.setCharacterEncoding("UTF-8");

        if (e.getCause()!= null && e.getCause() instanceof ParsingTokenException)
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        else
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        ErrorApiResponse error = ErrorApiResponse.of(e);
        httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(error));
    }
}
