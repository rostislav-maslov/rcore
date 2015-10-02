package com.ub.google.controllers;

import com.ub.core.base.utils.RouteUtils;
import com.ub.google.models.AppPropertiesGoogleDoc;
import com.ub.google.routes.GooglePlusRoutes;
import com.ub.google.services.GooglePlusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Eduard on 01.10.2015.
 */
@Controller
public class GooglePlusAdminController {

    @Autowired private GooglePlusService service;

    @RequestMapping(value = GooglePlusRoutes.EDIT, method = RequestMethod.GET)
    public String edit(Model model) {
        model.addAttribute("appPropertiesGoogleDoc", service.getGoogleProperties());
        return "com.ub.google.admin.appprop.edit";
    }

    @RequestMapping(value = GooglePlusRoutes.EDIT, method = RequestMethod.POST)
    public String edit(@ModelAttribute AppPropertiesGoogleDoc appPropertiesGoogleDoc,
                       Model model) {
        service.save(appPropertiesGoogleDoc);
        return RouteUtils.redirectTo(GooglePlusRoutes.EDIT);
    }
}
