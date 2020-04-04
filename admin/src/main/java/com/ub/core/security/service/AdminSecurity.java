package com.ub.core.security.service;

import com.ub.core.base.role.BaseAdminRole;
import com.ub.core.base.role.Role;
import com.ub.core.security.service.exceptions.UserNotAutorizedException;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.routes.UserLoginRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminSecurity extends HandlerInterceptorAdapter {
    @Autowired    private AutorizationService autorizationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            UserDoc userDoc = autorizationService.getUserFromSession();
            for(Role role : userDoc.getRoles()){
                BaseAdminRole baseAdminRole = new BaseAdminRole();
                if(role.getId().equals(baseAdminRole.getId())) {
                    return true;
                }
            }
        } catch (UserNotAutorizedException e) {
        }
        response.sendRedirect(UserLoginRoutes.LOGIN);

        return false;
    }




}

