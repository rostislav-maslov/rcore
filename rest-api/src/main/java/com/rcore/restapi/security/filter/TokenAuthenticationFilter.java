package com.rcore.restapi.security.filter;

import com.rcore.adapter.domain.token.dto.AccessTokenDTO;
import com.rcore.adapter.domain.token.mapper.AccessTokenMapper;
import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.port.AccessTokenStorage;
import com.rcore.domain.user.exception.UserNotExistException;
import com.rcore.restapi.headers.WebHeaders;
import com.rcore.restapi.routes.BaseRoutes;
import com.rcore.restapi.security.exceptions.ApiAuthenticationException;
import com.rcore.restapi.security.exceptions.InvalidTokenFormatApiException;
import com.rcore.restapi.security.exceptions.UnauthorizedApiException;
import com.rcore.restapi.security.exceptions.UserNotExistApiException;
import com.rcore.restapi.security.factory.AuthenticationTokenFactory;
import com.rcore.security.infrastructure.AuthTokenGenerator;
import com.rcore.security.infrastructure.exceptions.InvalidTokenFormatException;
import com.rcore.security.infrastructure.exceptions.TokenGenerateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
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
import java.util.Optional;

@Order(1)
@Component
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Value("${rcore.security.jwt.key}")
    private String secret;

    @Autowired
    private AuthTokenGenerator<AccessTokenDTO> authTokenGenerator;

    @Autowired
    private AccessTokenStorage accessTokenStorage;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationFailureHandler authenticationFailureHandler) {
        super(BaseRoutes.API + "/**");
        setAuthenticationManager(authenticationManager);
        setAuthenticationFailureHandler(authenticationFailureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        final String token = request.getHeader(WebHeaders.X_AUTH_TOKEN);

        if (!StringUtils.hasText(token))
            throw new UnauthorizedApiException();

        AccessTokenDTO accessToken = null;

        if (StringUtils.hasText(token)) {
            Optional<AccessTokenEntity> accessTokenEntity = null;
            try {
                accessTokenEntity = accessTokenStorage.findById(authTokenGenerator.parseToken(token, secret).getId());
            } catch (InvalidTokenFormatException | TokenGenerateException e) {
                throw new InvalidTokenFormatApiException();
            } catch (com.rcore.domain.token.exception.AuthenticationException | UserNotExistException e) {
                throw new UserNotExistApiException();
            }
            if (!accessTokenEntity.isPresent())
                throw new UserNotExistApiException();

            accessToken = new AccessTokenMapper().map(accessTokenEntity.get());

            try {
                if (!authTokenGenerator.generate(accessToken, secret).equals(token))
                    throw new UserNotExistException();
            } catch (TokenGenerateException e) {
                throw new InvalidTokenFormatApiException();
            } catch (UserNotExistException e) {
                throw new UserNotExistApiException();
            }
        }

        return getAuthenticationManager().authenticate(AuthenticationTokenFactory.ofRawToken(accessToken, token));
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
