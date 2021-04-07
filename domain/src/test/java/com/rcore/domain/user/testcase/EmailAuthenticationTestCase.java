package com.rcore.domain.user.testcase;


import com.rcore.domain.token.entity.TokenPair;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.RefreshTokenCreationException;
import com.rcore.domain.user.UserAppConfig;
import com.rcore.domain.user.exception.InvalidEmailException;
import com.rcore.domain.user.exception.InvalidPasswordException;
import com.rcore.domain.user.exception.UserBlockedException;
import com.rcore.domain.user.exception.UserNotFoundException;
import com.rcore.domain.user.testdata.AuthenticationTestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmailAuthenticationTestCase {
    private final UserAppConfig userConfig = new UserAppConfig();

    @Before
    public void before() {
        userConfig.getUserRepository().save(AuthenticationTestData.user1(userConfig.getPasswordGenerator()));
        userConfig.getUserRepository().save(AuthenticationTestData.user2(userConfig.getPasswordGenerator()));
    }

    @Test
    public void authentication() throws UserNotFoundException, UserBlockedException, InvalidPasswordException, InvalidEmailException {
        TokenPair tokenPair = userConfig.getUserConfig().all.authenticationUseCase().authentication("user1", "123");

        Assert.assertEquals(tokenPair.getAccessToken().getUserId(), "user1");

    }

}
