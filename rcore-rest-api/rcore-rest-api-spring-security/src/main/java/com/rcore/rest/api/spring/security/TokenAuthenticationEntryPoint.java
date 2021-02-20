package com.rcore.rest.api.spring.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcore.rest.api.commons.response.ErrorApiResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletRequest.setCharacterEncoding("UTF-8");

        ErrorApiResponse error = ErrorApiResponse.of(e);
        httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(error));
    }
}
