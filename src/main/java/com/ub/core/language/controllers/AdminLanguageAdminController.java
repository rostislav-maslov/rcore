package com.ub.core.language.controllers;

import com.ub.core.base.routes.BaseRoutes;
import com.ub.core.base.utils.RouteUtils;
import com.ub.core.language.routes.LanguageAdminRoutes;
import com.ub.core.language.services.AdminLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminLanguageAdminController {

    @Autowired private AdminLanguageService adminLanguageService;


    @RequestMapping(value = LanguageAdminRoutes.CHANGE, method = RequestMethod.GET)
    public String change(@RequestParam String lang, HttpServletRequest request, RedirectAttributes ra) {
        adminLanguageService.setLanguageCode(lang);

        return RouteUtils.redirectTo(BaseRoutes.ADMIN);
    }
}
