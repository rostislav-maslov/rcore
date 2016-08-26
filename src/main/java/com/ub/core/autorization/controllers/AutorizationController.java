package com.ub.core.autorization.controllers;

import com.ub.core.base.utils.RouteUtils;
import com.ub.core.security.service.AutorizationService;
import com.ub.core.security.service.exceptions.UserBlockedException;
import com.ub.core.security.service.exceptions.UserNotAutorizedException;
import com.ub.core.user.routes.UserLoginRoutes;
import com.ub.core.user.service.EmailSessionService;
import com.ub.core.user.service.exceptions.UserPasswordErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AutorizationController {

    @Autowired private AutorizationService autorizationService;
    @Autowired private EmailSessionService emailSessionService;


    @RequestMapping(value = UserLoginRoutes.LOGIN, method = RequestMethod.GET)
    public String loginToAdmin(Model model) {
        return "com.ub.core.admin.login";
    }

    @RequestMapping(value = UserLoginRoutes.ACCESS_DENIED, method = RequestMethod.GET)
    public String accessDenied(Model model) {
        return "com.ub.core.admin.access_denied";
    }

    @RequestMapping(value = UserLoginRoutes.LOGIN, method = RequestMethod.POST)
    public String loginToAdmin(@RequestParam String email, @RequestParam String password, Model model) {
        try {
            emailSessionService.autorize(email, password);
        } catch (UserNotAutorizedException | UserPasswordErrorException | UserBlockedException e) {
            model.addAttribute("error","true");
            return "com.ub.core.admin.login";
        }

        return RouteUtils.redirectTo("/admin");
    }

    @RequestMapping(value = UserLoginRoutes.LOGOUT, method = RequestMethod.GET)
    public String logout() {
        autorizationService.logout();
        return RouteUtils.redirectTo("/");
    }
}
