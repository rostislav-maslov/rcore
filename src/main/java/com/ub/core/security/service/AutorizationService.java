package com.ub.core.security.service;

import com.ub.core.base.role.Role;
import com.ub.core.base.role.SuperUser;
import com.ub.core.role.service.RoleService;
import com.ub.core.security.annotationSecurity.AvailableForLoggedUsers;
import com.ub.core.security.annotationSecurity.AvailableForRoles;
import com.ub.core.security.models.CheckAvailable;
import com.ub.core.security.service.exceptions.UserNotAutorizedException;
import com.ub.core.security.session.SessionModel;
import com.ub.core.security.session.SessionType;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.service.UserService;
import com.ub.core.user.service.exceptions.UserExistException;
import com.ub.vk.response.AccessTokenResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpSession;
import java.util.*;

@Component
public class AutorizationService {

    @Autowired private UserService userService;
    @Autowired private RoleService roleService;


    public CheckAvailable checkAccess(HandlerMethod handlerMethod) {
        CheckAvailable checkAvailable = new CheckAvailable();
        checkAvailable.setLogged(true);
        checkAvailable.setNeedRole(false);
        try {
            UserDoc userDoc = null;

            if (handlerMethod.getMethod().isAnnotationPresent(AvailableForLoggedUsers.class)) {
                AvailableForLoggedUsers availableForLoggedUsers = handlerMethod.getMethod().getAnnotation(AvailableForLoggedUsers.class);
                checkAvailable.setGoAfterFailLogin(availableForLoggedUsers.value());
                userDoc = getUserFromSession();
            }
            if (handlerMethod.getBeanType().isAnnotationPresent(AvailableForLoggedUsers.class) && userDoc == null) {
                AvailableForLoggedUsers availableForLoggedUsers = handlerMethod.getBeanType().getAnnotation(AvailableForLoggedUsers.class);
                checkAvailable.setGoAfterFailLogin(availableForLoggedUsers.value());
                userDoc = getUserFromSession();
            }

            Set<String> roleNames = null;
            Boolean isSuperUser = false;
            if (handlerMethod.getMethod().isAnnotationPresent(AvailableForRoles.class)) {
                if (userDoc == null) userDoc = getUserFromSession();
                roleNames = new HashSet<String>();
                for (Role role : userDoc.getRoles()) {
                    roleNames.add(role.getId());
                    if(role.getId().equals(new SuperUser().getId())){
                        isSuperUser = true;
                        return checkAvailable;
                    }
                }

                AvailableForRoles availableForRoles = handlerMethod.getMethod().getAnnotation(AvailableForRoles.class);
                for (Class<? extends Role> roleClass : availableForRoles.value()) {
                    if (roleNames.contains(roleClass.getName()) == false) {
                        checkAvailable.setNeedRole(true);
                        checkAvailable.setRole(new Role(roleService.findById(roleClass.getName())));
                        return checkAvailable;
                    }
                }
            }

            if (handlerMethod.getBeanType().isAnnotationPresent(AvailableForRoles.class)) {
                if (userDoc == null) userDoc = getUserFromSession();
                if (roleNames == null) {
                    roleNames = new HashSet<String>();
                    for (Role role : userDoc.getRoles()) {
                        roleNames.add(role.getId());
                        if(role.getId().equals(new SuperUser().getId())){
                            isSuperUser = true;
                            return checkAvailable;
                        }
                    }
                }

                AvailableForRoles availableForRoles = handlerMethod.getBeanType().getAnnotation(AvailableForRoles.class);
                for (Class<? extends Role> roleClass : availableForRoles.value()) {
                    if (roleNames.contains(roleClass.getName()) == false) {
                        checkAvailable.setNeedRole(true);
                        checkAvailable.setRole(new Role(roleService.findById(roleClass.getName())));
                        return checkAvailable;
                    }
                }
            }
        } catch (UserNotAutorizedException e) {
            checkAvailable.setLogged(false);
            return checkAvailable;
        }

        return checkAvailable;
    }

    public HttpSession getSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

    public String getTokenEmail(UserDoc userDoc) {
        String t = userDoc.getEmail() + ";" + userDoc.getPassword() + "42";
        return DigestUtils.md5Hex(t);
    }

    public String getTokenVk(UserDoc userDoc) {
        String t = userDoc.getVkId() + ";" + userDoc.getVkAccessToken() + "42";
        return DigestUtils.md5Hex(t);
    }

    public SessionModel getSessionModelEmailType(UserDoc userDoc) {
        SessionModel sessionModel = new SessionModel();
        sessionModel.setIdUser(userDoc.getId());
        sessionModel.setType(SessionType.EMAIL);
        sessionModel.setToken(getTokenEmail(userDoc));
        return sessionModel;
    }

    public SessionModel getSessionModelVkType(UserDoc userDoc) {
        SessionModel sessionModel = new SessionModel();
        sessionModel.setIdUser(userDoc.getId());
        sessionModel.setType(SessionType.VK);
        sessionModel.setToken(getTokenVk(userDoc));
        return sessionModel;
    }

    public void logout() {
        HttpSession httpSession = getSession();
        Enumeration enumerator = httpSession.getAttributeNames();
        while (enumerator.hasMoreElements()) {
            Object o = enumerator.nextElement();
            httpSession.removeAttribute(o.toString());
        }
        //httpSession.removeAttribute(SessionModel.ID_USER);
        //httpSession.removeAttribute(SessionModel.TOKEN);
        //httpSession.removeAttribute(SessionModel.TYPE);
    }

    public void autorizeEmailHashedPassword(String email, String hashedPassword) throws UserNotAutorizedException {
        UserDoc userDoc = userService.getUserByEmail(email);
        if (userDoc == null || userDoc.getPassword() == null) throw new UserNotAutorizedException();
        if (!userDoc.getPassword().equals(hashedPassword))
            throw new UserNotAutorizedException();
        SessionModel sessionModel = getSessionModelEmailType(userDoc);
        HttpSession httpSession = getSession();
        httpSession = sessionModel.fillSession(httpSession);
        httpSession.setMaxInactiveInterval(60 * 60 * 24 * 3);
    }

    public UserDoc autorizeEmail(String email, String password) throws UserNotAutorizedException {
        UserDoc userDoc = userService.getUserByEmail(email);
        if (userDoc == null || userDoc.getPassword() == null) throw new UserNotAutorizedException();
        if (!userDoc.getPassword().equals(UserDoc.generateHexPassword(email, password)))
            throw new UserNotAutorizedException();
        return authorizeUserDoc(userDoc);
    }

    public UserDoc authorizeUserDoc(UserDoc userDoc){
        SessionModel sessionModel = getSessionModelEmailType(userDoc);
        HttpSession httpSession = getSession();
        httpSession = sessionModel.fillSession(httpSession);
        httpSession.setMaxInactiveInterval(60 * 60 * 24 * 3);
        return userDoc;
    }

    public void authorizeVk(AccessTokenResponse accessTokenResponse) {
        UserDoc userDoc = userService.getUserByVkId(accessTokenResponse.getUser_id());
        if (userDoc == null) {
            try {
                userDoc = userService.createUserByVk(accessTokenResponse);
            } catch (UserExistException e) {
                e.printStackTrace();
            }
        } else {
            userDoc = userService.updateVkAccessToken(userDoc, accessTokenResponse.getAccess_token());
        }
        SessionModel sessionModel = getSessionModelVkType(userDoc);
        HttpSession httpSession = getSession();
        httpSession = sessionModel.fillSession(httpSession);
        httpSession.setMaxInactiveInterval(60 * 60 * 24 * 30);
    }

    public UserDoc getUserFromSession() throws UserNotAutorizedException {
        HttpSession httpSession = getSession();
        SessionModel sessionModel = null;
        try {
            sessionModel = new SessionModel(httpSession);
        } catch (NullPointerException e) {
        }

        if (sessionModel.getType() == null) throw new UserNotAutorizedException();
        if (sessionModel.getToken() == null) throw new UserNotAutorizedException();
        if (sessionModel.getIdUser() == null) throw new UserNotAutorizedException();
        if ((sessionModel.getType().equals(SessionType.EMAIL))) {
            UserDoc user = userService.getUser(sessionModel.getIdUser());
            if (user == null) throw new UserNotAutorizedException();
            if (sessionModel.getType().equals(SessionType.EMAIL) && getTokenEmail(user).equals(sessionModel.getToken()))
                return user;
        }
        if ((sessionModel.getType().equals(SessionType.VK))) {
            UserDoc user = userService.getUser(sessionModel.getIdUser());
            if (user == null) throw new UserNotAutorizedException();
            if (checkAccessTokenVk(user, sessionModel.getToken()) == false) {
                throw new UserNotAutorizedException();
            } else {
                return user;
            }
        }

        throw new UserNotAutorizedException();
    }

    private Boolean checkAccessTokenVk(UserDoc userDoc, String ourToken) {
        if (ourToken.equals(getTokenVk(userDoc)))
            return true;
        return false;
    }

}
