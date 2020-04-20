package com.rcore.restapi.security.filter;

import com.rcore.adapter.domain.token.dto.AccessTokenDTO;
import com.rcore.restapi.headers.WebHeaders;
import com.rcore.restapi.routes.BaseRoutes;
import com.rcore.restapi.security.exceptions.ApiAuthenticationException;
import com.rcore.restapi.security.exceptions.InvalidTokenFormatApiException;
import com.rcore.restapi.security.factory.AuthenticationTokenFactory;
import com.rcore.security.infrastructure.AuthTokenGenerator;
import com.rcore.security.infrastructure.exceptions.InvalidTokenFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Value("${rcore.security.jwt.key}")
    private String secret;

    @Autowired
    private AuthTokenGenerator<AccessTokenDTO> authTokenGenerator;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationFailureHandler authenticationFailureHandler) {
        super(BaseRoutes.API + "/**");
        setAuthenticationManager(authenticationManager);
        setAuthenticationFailureHandler(authenticationFailureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        final String token = request.getHeader(WebHeaders.X_AUTH_TOKEN);

        AccessTokenDTO accessToken = null;

        if (!StringUtils.hasText(token))
            new AccessDeniedException("");

        try {
            accessToken = authTokenGenerator.parseToken(token, secret);
        } catch (InvalidTokenFormatException e) {
            throw new InvalidTokenFormatApiException();
        }

        return getAuthenticationManager().authenticate(AuthenticationTokenFactory.ofRawToken(accessToken));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        getFailureHandler().onAuthenticationFailure(request, response, failed);
    }
}
