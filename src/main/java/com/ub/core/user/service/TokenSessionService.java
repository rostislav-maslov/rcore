package com.ub.core.user.service;

import com.ub.core.security.service.exceptions.UserNotAutorizedException;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.models.UserStatusEnum;
import com.ub.core.user.models.UserToken;
import com.ub.core.user.service.exceptions.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenSessionService {

    public static final Integer LIMIT_FAILS = 30;

    @Autowired private EmailSessionService emailSessionService;
    @Autowired private UserService userService;

    public UserToken generateNewRefreshTokenByAccessToken(String accessToken) throws UserNotAutorizedException {
        UserDoc userDoc = getUserFromToken(accessToken);
        if (userDoc.checkAccessToken(accessToken) == false) {
            throw new UserNotAutorizedException();
        }

        UserToken refreshToken = getRefreshToken(userDoc);
        return refreshToken;
    }

    public UserToken generateNewAccessTokenByEmailPassword(String email, String password) throws UserNotAutorizedException {
        UserDoc userDoc = userService.findByEmail(email);
        if (userDoc == null || userDoc.getPassword() == null || userDoc.getUserStatus().equals(UserStatusEnum.BLOCK))
            throw new UserNotAutorizedException();
        if (!userDoc.getPassword().equals(password))
            throw new UserNotAutorizedException();

        return getToken(userDoc);
    }

    public UserToken generateNewAccessTokenByVk(String vkId) throws UserNotAutorizedException {
        UserDoc userDoc = userService.getUserByVkId(vkId);
        if (userDoc == null || userDoc.getUserStatus().equals(UserStatusEnum.BLOCK))
            throw new UserNotAutorizedException();

        return getToken(userDoc);
    }

    public UserToken generateNewAccessTokenByFb(String fbId) throws UserNotAutorizedException {
        UserDoc userDoc = userService.getUserByFbId(fbId);
        if (userDoc == null || userDoc.getUserStatus().equals(UserStatusEnum.BLOCK))
            throw new UserNotAutorizedException();

        return getToken(userDoc);
    }

    public UserToken generateNewAccessTokenByRefreshToken(String refreshToken) throws UserNotAutorizedException {
        UserDoc userDoc = getUserFromRefreshToken(refreshToken);
        return getToken(userDoc);
    }

    public UserDoc getUserFromToken(String token) throws UserNotAutorizedException {
        UserDoc userDoc = userService.findByAccessToken(token);

        if (userDoc != null) {
            return userDoc;
        }

        throw new UserNotAutorizedException();
    }

    public UserDoc getUserFromRefreshToken(String token) throws UserNotAutorizedException {
        UserDoc userDoc = userService.findByRefreshToken(token);

        if (userDoc != null) {
            return userDoc;
        }

        throw new UserNotAutorizedException();
    }

    private UserToken getToken(UserDoc userDoc) {
        UserToken userToken = userDoc.getActiveAccessToken();
        try {
            if (userToken == null) {
                userToken = UserDoc.generateNewToken(userDoc);
                userDoc.addAccessToken(userToken);
                userDoc = userService.save(userDoc);
            }
        } catch (UserExistException e) {
            e.printStackTrace();
        }
        return userToken;
    }

    private UserToken getRefreshToken(UserDoc userDoc) {
        UserToken userToken = userDoc.getActiveRefreshToken();
        try {
            if (userToken == null) {
                userToken = UserDoc.generateNewToken(userDoc);
                userDoc.addRefreshToken(userToken);
                userDoc = userService.save(userDoc);
            }
        } catch (UserExistException e) {
            e.printStackTrace();
        }
        return userToken;
    }

//    private SessionModel getSessionModel(UserDoc userDoc) {
//        SessionModel sessionModel = new SessionModel();
//        sessionModel.setIdUser(userDoc.getId());
//        sessionModel.setType(SessionType.TOKEN);
//        sessionModel.setToken(getToken(userDoc));
//
//        return sessionModel;
//    }
}
