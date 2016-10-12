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
import com.ub.core.user.service.EmailSessionService;
import com.ub.core.user.service.LoginSessionService;
import com.ub.core.user.service.UserService;
import com.ub.facebook.services.FBSessionService;
import com.ub.google.services.GPSessionService;
import com.ub.linkedin.services.LiSessionService;
import com.ub.odnoklassniki.services.OkSessionService;
import com.ub.twitter.services.TwitterSessionService;
import com.ub.vk.services.VkSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Component
public class AutorizationService {

    @Autowired private UserService userService;
    @Autowired private RoleService roleService;
    @Autowired private FBSessionService fbSessionService;
    @Autowired private EmailSessionService emailSessionService;
    @Autowired private LoginSessionService loginSessionService;
    @Autowired private VkSessionService vkSessionService;
    @Autowired private GPSessionService gpSessionService;
    @Autowired private TwitterSessionService twitterSessionService;
    @Autowired private LiSessionService liSessionService;
    @Autowired private OkSessionService okSessionService;

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
                    if (role.getId().equals(new SuperUser().getId())) {
                        isSuperUser = true;
                        return checkAvailable;
                    }
                }

                //Если хоть одна роль есть, значит можно пустить по урлу
                AvailableForRoles availableForRoles = handlerMethod.getMethod().getAnnotation(AvailableForRoles.class);

                if (availableForRoles.value().length > 0) {
                    boolean needReturn = true;
                    for (Class<? extends Role> roleClass : availableForRoles.value()) {
                        if (roleNames.contains(roleClass.getName()) == true) {
                            needReturn = false;
                        }
                    }

                    if (needReturn) {
                        Class<? extends Role> roleClass = availableForRoles.value()[0];
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
                        if (role.getId().equals(new SuperUser().getId())) {
                            isSuperUser = true;
                            return checkAvailable;
                        }
                    }
                }

                //Если хоть одна роль есть, значит можно пустить по урлу
                AvailableForRoles availableForRoles = handlerMethod.getBeanType().getAnnotation(AvailableForRoles.class);
                if (availableForRoles.value().length > 0) {
                    boolean needReturn = true;
                    for (Class<? extends Role> roleClass : availableForRoles.value()) {
                        if (roleNames.contains(roleClass.getName()) == true) {
                            needReturn = false;
                        }
                    }
                    if (needReturn) {
                        Class<? extends Role> roleClass = availableForRoles.value()[0];
                        checkAvailable.setNeedRole(true);
                        Role role = new Role(roleService.findById(roleClass.getName()));
                        checkAvailable.setRole(role);
                        checkAvailable.setGoAfterFailLogin(role.getGoAfterFail());
                        return checkAvailable;
                    }

                }

            }
        } catch (UserNotAutorizedException e) {
            checkAvailable.setLogged(false);

            if (handlerMethod.getBeanType().isAnnotationPresent(AvailableForRoles.class)) {
                AvailableForRoles availableForRoles = handlerMethod.getBeanType().getAnnotation(AvailableForRoles.class);
                if (availableForRoles != null) {
                    for (Class<? extends Role> roleClass : availableForRoles.value()) {
                        Role role = new Role(roleService.findById(roleClass.getName()));
                        checkAvailable.setGoAfterFailLogin(role.getGoAfterFail());
                    }
                }
            }
            if (handlerMethod.getMethod().isAnnotationPresent(AvailableForRoles.class)) {
                AvailableForRoles availableForRoles = handlerMethod.getMethod().getAnnotation(AvailableForRoles.class);
                if (availableForRoles != null) {
                    for (Class<? extends Role> roleClass : availableForRoles.value()) {
                        Role role = new Role(roleService.findById(roleClass.getName()));
                        checkAvailable.setGoAfterFailLogin(role.getGoAfterFail());
                    }
                }
            }

            return checkAvailable;
        }

        return checkAvailable;
    }

    public static HttpSession getSession() {
        return ASessionConfigService.getSession();
    }

    public void logout() {
        ASessionConfigService.logout();
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
            return emailSessionService.getUserFromSession(sessionModel);
        }
        if ((sessionModel.getType().equals(SessionType.LOGIN))) {
            return loginSessionService.getUserFromSession(sessionModel);
        }
        if ((sessionModel.getType().equals(SessionType.VK))) {
            return vkSessionService.getUserFromSession(sessionModel);
        }
        if (sessionModel.getType().equals(SessionType.GOOGLE)) {
            return gpSessionService.getUserFromSession(sessionModel);
        }
        if (sessionModel.getType().equals(SessionType.TWITTER)) {
            return twitterSessionService.getUserFromSession(sessionModel);
        }
        if (sessionModel.getType().equals(SessionType.FB)) {
            return fbSessionService.getUserFromSession(sessionModel);
        }
        if (sessionModel.getType().equals(SessionType.LINKEDIN)) {
            return liSessionService.getUserFromSession(sessionModel);
        }
        if (sessionModel.getType().equals(SessionType.OK)) {
            return okSessionService.getUserFromSession(sessionModel);
        }

        throw new UserNotAutorizedException();
    }


}
