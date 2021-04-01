package com.rcore.database.mongo.user.testdata;


import com.rcore.database.mongo.config.TestAppConfig;
import com.rcore.database.mongo.domain.user.model.UserDoc;
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

public class EmailAuthenticationTestCase {
    private final TestAppConfig testAppConfig = new TestAppConfig();

    public EmailAuthenticationTestCase() throws Exception {
    }

    @Before
    public void before() {
        UserDoc user1 = UserDoc.toDoc(testAppConfig.getUserRepository().save(AuthenticationTestData.user1(testAppConfig.getPasswordGenerator())));
        UserDoc user2 = UserDoc.toDoc(testAppConfig.getUserRepository().save(AuthenticationTestData.user2(testAppConfig.getPasswordGenerator())));
    }

    @After
    public void after() {
    }

    @Test
    public void authentication() throws UserNotFoundException, UserBlockedException, AuthenticationException, RefreshTokenCreationException {
        TokenPair tokenPair = testAppConfig.getUserConfig().all.authenticationUseCase().authentication("user1", "123");

        Assert.assertEquals(tokenPair.getAccessToken().getUserId(), "user1");

    }

}
