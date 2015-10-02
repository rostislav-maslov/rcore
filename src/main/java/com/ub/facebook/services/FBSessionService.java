package com.ub.facebook.services;

import com.ub.core.security.service.ASessionConfigService;
import com.ub.core.security.service.exceptions.UserNotAutorizedException;
import com.ub.core.security.session.SessionModel;
import com.ub.core.security.session.SessionType;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.service.exceptions.UserExistException;
import com.ub.facebook.response.FBUserInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class FBSessionService extends ASessionConfigService {


    @Override
    public UserDoc getUserFromSession(SessionModel sessionModel) throws UserNotAutorizedException {
        if ((sessionModel.getType().equals(SessionType.FB))) {
            UserDoc user = userService.getUser(sessionModel.getIdUser());
            if (user == null) throw new UserNotAutorizedException();
            if (checkAccessToken(user, sessionModel.getToken()) == false) {
                throw new UserNotAutorizedException();
            } else {
                return user;
            }
        }
        throw new UserNotAutorizedException();
    }

    @Override
    public String getToken(UserDoc userDoc) {
        String t = userDoc.getFbId() + ";" + userDoc.getFbAccessToken() + "42";
        return DigestUtils.md5Hex(t);
    }

    @Override
    public SessionModel getSessionModel(UserDoc userDoc) {
        SessionModel sessionModel = new SessionModel();
        sessionModel.setIdUser(userDoc.getId());
        sessionModel.setType(SessionType.FB);
        sessionModel.setToken(getToken(userDoc));
        return sessionModel;
    }

    public UserDoc authorize(FBUserInfo userInfo) {
        UserDoc userDoc = userService.getUserByFbId(userInfo.getId());
        if (userDoc == null) {
            try {
                userDoc = userService.createUserByFb(userInfo);
            } catch (UserExistException e) {
                e.printStackTrace();
            }
        } else {
            userDoc = userService.updateFbAccessToken(userDoc, userInfo.getAccessToken());
        }
        SessionModel sessionModel = getSessionModel(userDoc);
        HttpSession httpSession = getSession();
        httpSession = sessionModel.fillSession(httpSession);
        httpSession.setMaxInactiveInterval(60 * 60 * 24 * 30);

        return userDoc;
    }


}
