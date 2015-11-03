package com.ub.twitter.services;

import com.ub.core.security.service.ASessionConfigService;
import com.ub.core.security.service.exceptions.UserNotAutorizedException;
import com.ub.core.security.session.SessionModel;
import com.ub.core.security.session.SessionType;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.service.exceptions.UserExistException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import twitter4j.User;
import twitter4j.auth.AccessToken;

import javax.servlet.http.HttpSession;

@Component
public class TwitterSessionService extends ASessionConfigService {
    public UserDoc authorizeTwitter(User user, AccessToken accessToken) {
        UserDoc userDoc = userService.getUserByTwitterId(String.valueOf(user.getId()));
        if (userDoc == null) {
            try {
                userDoc = userService.createUserByTwitter(user, accessToken.getToken(), accessToken.getTokenSecret());
            } catch (UserExistException e) {
                e.printStackTrace();
            }
        } else {
            userDoc = userService.updateTwitterAccesToken(userDoc, accessToken);
        }

        SessionModel sessionModel = getSessionModel(userDoc);
        HttpSession httpSession = getSession();
        httpSession = sessionModel.fillSession(httpSession);
        httpSession.setMaxInactiveInterval(60 * 60 * 24 * 30);

        return userDoc;
    }

    @Override
    public UserDoc getUserFromSession(SessionModel sessionModel) throws UserNotAutorizedException {
        if (sessionModel.getType().equals(SessionType.TWITTER)) {
            UserDoc user = userService.getUser(sessionModel.getIdUser());
            if (user == null) throw new UserNotAutorizedException();
            if (!checkAccessToken(user, sessionModel.getToken())) {
                throw new UserNotAutorizedException();
            } else {
                return user;
            }
        }
        throw new UserNotAutorizedException();
    }

    @Override
    public String getToken(UserDoc userDoc) {
        String t = userDoc.getTwitterId() + ";" + userDoc.getTwitterAccessToken() + "42";
        return DigestUtils.md5Hex(t);
    }

    @Override
    public SessionModel getSessionModel(UserDoc userDoc) {
            SessionModel sessionModel = new SessionModel();
            sessionModel.setIdUser(userDoc.getId());
            sessionModel.setType(SessionType.TWITTER);
            sessionModel.setToken(getToken(userDoc));
            return sessionModel;
    }
}
