package com.ub.core.user.service;

import com.ub.core.security.service.ASessionConfigService;
import com.ub.core.security.service.exceptions.UserBlockedException;
import com.ub.core.security.service.exceptions.UserNotAutorizedException;
import com.ub.core.security.session.SessionModel;
import com.ub.core.security.session.SessionType;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.service.exceptions.UserPasswordErrorException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class EmailSessionService extends ASessionConfigService {
    public UserDoc autorize(String email, String password) throws UserNotAutorizedException, UserPasswordErrorException, UserBlockedException {

        return autorizeEmailHashedPassword(email, UserDoc.generateHexPassword(email, password));
    }

    public UserDoc autorize(String email, String password, String lang) throws UserNotAutorizedException, UserPasswordErrorException, UserBlockedException {
        return autorize(email, password);
    }

    public UserDoc autorizeEmailHashedPassword(String email, String hashedPassword) throws UserNotAutorizedException, UserPasswordErrorException, UserBlockedException {

        return authorizeUserDoc(userService.validateUserByEmail(email, hashedPassword));
    }

    public UserDoc authorizeUserDoc(UserDoc userDoc) {
        SessionModel sessionModel = getSessionModel(userDoc);
        HttpSession httpSession = getSession();
        httpSession = sessionModel.fillSession(httpSession);
        httpSession.setMaxInactiveInterval(60 * 60 * 24 * 3);
        return userDoc;
    }

    @Override
    public UserDoc getUserFromSession(SessionModel sessionModel) throws UserNotAutorizedException {
        if ((sessionModel.getType().equals(SessionType.EMAIL))) {
            UserDoc user = userService.getUser(sessionModel.getIdUser());
            if (user == null) throw new UserNotAutorizedException();
            if (sessionModel.getType().equals(SessionType.EMAIL) && getToken(user).equals(sessionModel.getToken()))
                return user;
        }
        throw new UserNotAutorizedException();
    }

    @Override
    public String getToken(UserDoc userDoc) {
        String t = userDoc.getEmail() + ";" + userDoc.getPassword() + "42";
        return DigestUtils.md5Hex(t);
    }

    @Override
    public SessionModel getSessionModel(UserDoc userDoc) {
        SessionModel sessionModel = new SessionModel();
        sessionModel.setIdUser(userDoc.getId());
        sessionModel.setType(SessionType.EMAIL);
        sessionModel.setToken(getToken(userDoc));
        return sessionModel;
    }
}
