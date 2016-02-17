package com.ub.core.user.service;

import com.ub.core.security.service.ASessionConfigService;
import com.ub.core.security.service.exceptions.UserBlockedException;
import com.ub.core.security.service.exceptions.UserNotAutorizedException;
import com.ub.core.security.session.SessionModel;
import com.ub.core.security.session.SessionType;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.models.UserStatusEnum;
import com.ub.core.user.service.exceptions.UserExistException;
import com.ub.core.user.service.exceptions.UserPasswordErrorException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginSessionService extends ASessionConfigService {

    public static final Integer LIMIT_FAILS = 30;

    @Autowired private EmailSessionService emailSessionService;

    public UserDoc authorize(String login, String password) throws UserNotAutorizedException {
        UserDoc userDoc = userService.findByLogin(login);
        if (userDoc == null || userDoc.getPasswordForLogin() == null || userDoc.getUserStatus().equals(UserStatusEnum.BLOCK))
            throw new UserNotAutorizedException();
        if (!userDoc.getPasswordForLogin().equals(UserDoc.generateHexPassword(login, password)))
            throw new UserNotAutorizedException();
        return emailSessionService.authorizeUserDoc(userDoc);
    }

    public UserDoc authorize(String login, String password, String lang) throws UserNotAutorizedException, UserBlockedException, UserPasswordErrorException, UserExistException {
        UserDoc userDoc = userService.findByLogin(login);
        if (userDoc == null || userDoc.getPasswordForLogin() == null)
            throw new UserNotAutorizedException();
        if (!userDoc.getPasswordForLogin().equals(UserDoc.generateHexPassword(login, password)))
            throw new UserPasswordErrorException();
        if (userDoc.getUserStatus().equals(UserStatusEnum.BLOCK))
            throw new UserBlockedException();
        if(userDoc.getFails() >=LIMIT_FAILS){
            userDoc.setUserStatus(UserStatusEnum.BLOCK);
            userService.save(userDoc);
            throw new UserBlockedException();
        }
        userDoc.setFails(0);
        userService.save(userDoc);

        return emailSessionService.authorizeUserDoc(userDoc);
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
