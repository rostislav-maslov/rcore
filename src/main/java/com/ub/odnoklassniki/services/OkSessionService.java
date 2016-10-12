package com.ub.odnoklassniki.services;

import com.ub.core.security.service.ASessionConfigService;
import com.ub.core.security.service.exceptions.UserNotAutorizedException;
import com.ub.core.security.session.SessionModel;
import com.ub.core.security.session.SessionType;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.service.exceptions.UserExistException;
import com.ub.odnoklassniki.response.OkUserInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class OkSessionService extends ASessionConfigService {
    @Override
    public UserDoc getUserFromSession(SessionModel sessionModel) throws UserNotAutorizedException {
        if ((sessionModel.getType().equals(SessionType.OK))) {
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
        String t = userDoc.getOkId() + ";" + userDoc.getOkAccessToken() + "42";
        return DigestUtils.md5Hex(t);
    }

    @Override
    public SessionModel getSessionModel(UserDoc userDoc) {
        SessionModel sessionModel = new SessionModel();
        sessionModel.setIdUser(userDoc.getId());
        sessionModel.setType(SessionType.OK);
        sessionModel.setToken(getToken(userDoc));
        return sessionModel;
    }

    public UserDoc authorize(OkUserInfo userInfo) {
        UserDoc userDoc = userService.getUserByOkId(userInfo.getUid());
        if (userDoc == null) {
            try {
                userDoc = userService.createUserByOk(userInfo);
            } catch (UserExistException e) {
                e.printStackTrace();
            }
        } else {
            userDoc = userService.updateOkAccessToken(userDoc, userInfo.getAccessToken());
        }
        SessionModel sessionModel = getSessionModel(userDoc);
        HttpSession httpSession = getSession();
        httpSession = sessionModel.fillSession(httpSession);
        httpSession.setMaxInactiveInterval(60 * 60 * 24 * 30);

        return userDoc;
    }
}
