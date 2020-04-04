package com.ub.linkedin.services;

import com.ub.core.security.service.ASessionConfigService;
import com.ub.core.security.service.exceptions.UserNotAutorizedException;
import com.ub.core.security.session.SessionModel;
import com.ub.core.security.session.SessionType;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.service.exceptions.UserExistException;
import com.ub.linkedin.response.LinkedinUserInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * Created by Eduard on 05.10.2015.
 */
@Component
public class LiSessionService extends ASessionConfigService {

    @Autowired private LinkedinService service;

    public UserDoc authorizeLinkedin(LinkedinUserInfo userInfo) {
        UserDoc userDoc = userService.getUserByLinkedinId(userInfo.getId());
        if (userDoc == null) {
            try {
                userDoc = userService.createUserByLinkedin(userInfo);
            } catch (UserExistException e) {
                e.printStackTrace();
            }
        } else {
            userDoc = userService.updateLinkedinAccessToken(userDoc, userInfo.getAccessToken());
        }

        SessionModel sessionModel = getSessionModel(userDoc);
        HttpSession httpSession = getSession();
        httpSession = sessionModel.fillSession(httpSession);
        httpSession.setMaxInactiveInterval(60 * 60 * 24 * 30);

        return userDoc;
    }

    @Override
    public UserDoc getUserFromSession(SessionModel sessionModel) throws UserNotAutorizedException {
        if (sessionModel.getType().equals(SessionType.LINKEDIN)) {
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
        String t = userDoc.getLinkedinId() + ";" + userDoc.getLinkedinAccessToken() + "42";
        return DigestUtils.md5Hex(t);
    }

    @Override
    public SessionModel getSessionModel(UserDoc userDoc) {
        SessionModel sessionModel = new SessionModel();
        sessionModel.setIdUser(userDoc.getId());
        sessionModel.setType(SessionType.LINKEDIN);
        sessionModel.setToken(getToken(userDoc));
        return sessionModel;
    }
}
