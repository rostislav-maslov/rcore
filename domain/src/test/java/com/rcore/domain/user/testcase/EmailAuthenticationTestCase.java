package com.rcore.domain.user.testcase;

import com.rcore.domain.base.TestAppConfig;
import com.rcore.domain.token.entity.TokenPair;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.RefreshTokenCreationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserBlockedException;
import com.rcore.domain.user.exception.UserNotFoundException;
import com.rcore.domain.user.testdata.AuthenticationTestData;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class EmailAuthenticationTestCase {
    private final TestAppConfig testAppConfig = new TestAppConfig();

    @Before
    public void before() {
//        Optional<UserEntity> user1 = testAppConfig.getUserRepository().save(AuthenticationTestData.user1(testAppConfig.getPasswordGenerator()));
//        Optional<UserEntity> user2 = testAppConfig.getUserRepository().save(AuthenticationTestData.user2(testAppConfig.getPasswordGenerator()));
    }

    @After
    public void after() {
    }

    @Test
    public void authentication() throws UserNotFoundException, UserBlockedException, AuthenticationException, RefreshTokenCreationException {
//        TokenPair tokenPair = testAppConfig.getUserConfig().all.emailAuthenticationUseCase().authentication("user1", "123");

//        Assert.assertEquals(tokenPair.getAccessToken().getUserId(), "user1");

    }

}
