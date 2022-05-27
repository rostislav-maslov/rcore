package com.rcore.rest.api.spring.security;

import com.rcore.domain.security.model.CredentialDetails;
import com.rcore.domain.security.port.AccessChecker;
import com.rcore.domain.security.port.CredentialIdentityService;
import com.rcore.rest.api.commons.header.WebHeaders;
import com.rcore.rest.api.commons.routes.BaseRoutes;
import com.rcore.rest.api.spring.security.exceptions.AuthenticationApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final AccessChecker accessChecker;
    private final String serviceName;

    public TokenAuthenticationFilter(
            AuthenticationManager authenticationManager,
            AuthenticationFailureHandler authenticationFailureHandler,
            AccessChecker accessChecker,
            String serviceName
    ) {
        super("/**");
        setAuthenticationManager(authenticationManager);
        setAuthenticationFailureHandler(authenticationFailureHandler);
        this.accessChecker = accessChecker;
        this.serviceName = serviceName;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        try {
            var token = request.getHeader(WebHeaders.X_AUTH_TOKEN);
            var credentialDetails = accessChecker.checkAccessByToken(token, request.getMethod(), request.getRequestURI(), serviceName);
            CredentialPrincipal credentialPrincipal = CredentialPrincipal.from(credentialDetails);
            var authentication = new TokenAuthentication(token, credentialPrincipal.getAuthorities(), true, credentialPrincipal);
            return getAuthenticationManager().authenticate(authentication);
        } catch (Exception e) {
            log.error("Attempt authentication error", e);
            throw new AuthenticationApiException("Attempt authentication error", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
