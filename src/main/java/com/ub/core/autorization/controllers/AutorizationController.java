package com.ub.core.autorization.controllers;

import com.ub.core.base.utils.RouteUtils;
import com.ub.core.autorization.routes.AutorizationRoutes;
import com.ub.core.security.service.AutorizationService;
import com.ub.core.security.service.exceptions.UserNotAutorizedException;
import com.ub.core.user.routes.UserLoginRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AutorizationController {

    @Autowired private AutorizationService autorizationService;

    @RequestMapping(value = AutorizationRoutes.LOGIN, method = RequestMethod.POST)
    public String autorization(@RequestParam String email, @RequestParam String password, Model model) {
        try {
            autorizationService.autorizeEmail(email, password);
        } catch (UserNotAutorizedException e) {
            model.addAttribute("title", "Ошибка при авторизации");
            model.addAttribute("description", "Пользователя с таким email и паролем не существует.");
            return "com.ub.dyl.client.registration.verification";
        }
        return RouteUtils.redirectTo("/");
    }

    @RequestMapping(value = UserLoginRoutes.LOGIN, method = RequestMethod.GET)
    public String loginToAdmin(Model model) {
        return "com.ub.core.admin.login";
    }

    @RequestMapping(value = UserLoginRoutes.LOGIN, method = RequestMethod.POST)
    public String loginToAdmin(@RequestParam String email, @RequestParam String password, Model model) {
        try {
            autorizationService.autorizeEmail(email, password);
        } catch (UserNotAutorizedException e) {
            return RouteUtils.redirectTo("/admin/login");
        }
        return RouteUtils.redirectTo("/admin");
    }

    @RequestMapping(value = AutorizationRoutes.LOGOUT, method = RequestMethod.GET)
    public String logout() {
        autorizationService.logout();
        return RouteUtils.redirectTo("/");
    }
}
