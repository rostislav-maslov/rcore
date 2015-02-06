package com.ub.core.security.service;

import com.ub.core.role.models.RoleDoc;
import com.ub.core.role.service.RoleService;
import com.ub.core.security.models.CheckAvailable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SecurityInterception extends HandlerInterceptorAdapter {
    @Autowired private AutorizationService autorizationService;
    @Autowired private RoleService roleService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if (!(handler instanceof HandlerMethod)) {
            return true;
        }


        CheckAvailable checkAvailable = autorizationService.checkAccess((HandlerMethod) handler);

        if (checkAvailable.getLogged() == false) {
            response.sendRedirect(checkAvailable.getGoAfterFailLogin());
            return false;
        }

        if (checkAvailable.getNeedRole() == true) {
            RoleDoc roleDoc = roleService.findById(checkAvailable.getRole().getId());
            response.sendRedirect(roleDoc.getGoAfterFail());
            return false;
        }

        return true;
    }


}
