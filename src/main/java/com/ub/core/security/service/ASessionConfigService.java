package com.ub.core.security.service;

import com.ub.core.security.service.exceptions.UserNotAutorizedException;
import com.ub.core.security.session.SessionModel;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Component
public abstract class ASessionConfigService {
    @Autowired protected UserService userService;

    public abstract UserDoc getUserFromSession(SessionModel sessionModel) throws UserNotAutorizedException;
    public abstract String getToken(UserDoc userDoc);
    public abstract SessionModel getSessionModel(UserDoc userDoc) ;

    //public UserDoc authorizeGoogle(GoogleUserInfo userInfo);

    public Boolean checkAccessToken(UserDoc userDoc, String ourToken) {
        if (ourToken.equals(getToken(userDoc)))
            return true;
        return false;
    }

    public static HttpSession getSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

    public static void logout() {
        HttpSession httpSession = getSession();
        Enumeration enumerator = httpSession.getAttributeNames();
        while (enumerator.hasMoreElements()) {
            Object o = enumerator.nextElement();
            httpSession.removeAttribute(o.toString());
        }
    }
}
