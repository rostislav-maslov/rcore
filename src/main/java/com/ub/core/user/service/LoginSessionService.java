package com.ub.core.user.service;

import com.ub.core.security.service.ASessionConfigService;
import com.ub.core.security.service.exceptions.UserBlockedException;
import com.ub.core.security.service.exceptions.UserNotAutorizedException;
import com.ub.core.security.session.SessionModel;
import com.ub.core.security.session.SessionType;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.service.exceptions.UserExistException;
import com.ub.core.user.service.exceptions.UserPasswordErrorException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginSessionService extends ASessionConfigService {

    @Autowired private EmailSessionService emailSessionService;

    public UserDoc authorize(String login, String password) throws UserNotAutorizedException, UserExistException, UserBlockedException, UserPasswordErrorException {

        return emailSessionService.authorizeUserDoc(userService.validateUserByLogin(login, UserDoc.generateHexPassword(login, password)));
    }

    public UserDoc authorize(String login, String password, String lang) throws UserNotAutorizedException, UserBlockedException, UserPasswordErrorException, UserExistException {
        return authorize(login, password);
    }

    @Override
    public UserDoc getUserFromSession(SessionModel sessionModel) throws UserNotAutorizedException {
        if ((sessionModel.getType().equals(SessionType.LOGIN))) {
            UserDoc user = userService.getUser(sessionModel.getIdUser());
            if (user == null) throw new UserNotAutorizedException();
            if (sessionModel.getType().equals(SessionType.LOGIN) && getToken(user).equals(sessionModel.getToken()))
                return user;
        }
        throw new UserNotAutorizedException();
    }

    @Override
    public String getToken(UserDoc userDoc) {
        String t = userDoc.getLogin() + ";" + userDoc.getPasswordForLogin() + "42";
        return DigestUtils.md5Hex(t);
    }

    @Override
    public SessionModel getSessionModel(UserDoc userDoc) {
        SessionModel sessionModel = new SessionModel();
        sessionModel.setIdUser(userDoc.getId());
        sessionModel.setType(SessionType.LOGIN);
        sessionModel.setToken(getToken(userDoc));

        return sessionModel;
    }
}
