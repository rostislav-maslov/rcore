package com.rcore.restapi.security.filter;

import com.rcore.adapter.domain.token.TokenAdapter;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.domain.user.exception.TokenExpiredException;
import com.rcore.domain.user.exception.UserBlockedException;
import com.rcore.domain.user.exception.UserNotExistException;
import com.rcore.restapi.security.exceptions.TokenExpiredApiException;
import com.rcore.restapi.security.exceptions.UserBlockedApiException;
import com.rcore.restapi.security.exceptions.UserNotExistApiException;
import com.rcore.restapi.security.model.AnonymousTokenAuthentication;
import com.rcore.restapi.security.model.TokenAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenAuthenticationManager implements AuthenticationManager {

    private final TokenAdapter tokenAdapter;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof TokenAuthentication) {
            TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
            UserDTO user = null;
            try {
                user = tokenAdapter.getUserByAccessToken(((TokenAuthentication) authentication).getToken());
            } catch (UserNotExistException e) {
                throw new UserNotExistApiException();
            } catch (UserBlockedException e) {
                throw new UserBlockedApiException();
            } catch (TokenExpiredException e) {
                throw new TokenExpiredApiException();
            }

            tokenAuthentication.setUser(user);
            tokenAuthentication.setAuthenticated(true);
            return tokenAuthentication;
        } else if (authentication instanceof AnonymousTokenAuthentication) {
            authentication.setAuthenticated(true);
            return authentication;
        } else
            return authentication;
    }
}
