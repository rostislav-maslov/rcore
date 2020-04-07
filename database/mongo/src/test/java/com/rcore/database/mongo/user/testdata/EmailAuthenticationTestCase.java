package com.rcore.database.mongo.user.testdata;


import com.rcore.database.mongo.config.TestAppConfig;
import com.rcore.database.mongo.user.port.model.UserDoc;
import com.rcore.database.mongo.user.usecase.AuthenticationTestData;
import com.rcore.domain.token.entity.TokenPair;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.RefreshTokenCreationException;
import com.rcore.domain.user.exception.UserBlockedException;
import com.rcore.domain.user.exception.UserNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class EmailAuthenticationTestCase extends Exception {
    private final TestAppConfig testAppConfig = new TestAppConfig();

    public EmailAuthenticationTestCase() throws Exception {
    }

    @Before
    public void before() {
        Optional<UserDoc> user1 = testAppConfig.getUserRepository().save(AuthenticationTestData.user1(testAppConfig.getPasswordGenerator()));
        Optional<UserDoc> user2 = testAppConfig.getUserRepository().save(AuthenticationTestData.user2(testAppConfig.getPasswordGenerator()));
    }

    @After
    public void after() {
    }

    @Test
    public void authentication() throws UserNotFoundException, UserBlockedException, AuthenticationException, RefreshTokenCreationException {
        TokenPair tokenPair = testAppConfig.getUserConfig().all.emailAuthenticationUseCase().authentication("user1", "123");

        Assert.assertEquals(tokenPair.getAccessToken().getUserId(), "user1");

    }

}
