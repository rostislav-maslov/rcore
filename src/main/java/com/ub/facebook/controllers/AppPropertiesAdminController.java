package com.ub.facebook.controllers;

import com.ub.core.base.utils.RouteUtils;
import com.ub.facebook.models.AppPropertiesFbDoc;
import com.ub.facebook.routes.AppPropertiesRoutes;
import com.ub.facebook.services.AppPropertiesFbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppPropertiesAdminController {
    @Autowired private AppPropertiesFbService appPropertiesFbService;

    @RequestMapping(value = AppPropertiesRoutes.EDIT, method = RequestMethod.GET)
    public String edit(Model model) {
        model.addAttribute("appPropertiesVkDoc", appPropertiesFbService.getFbProp());
        return "com.ub.fb.admin.appprop.edit";
    }

    @RequestMapping(value = AppPropertiesRoutes.EDIT, method = RequestMethod.POST)
    public String edit(@ModelAttribute AppPropertiesFbDoc appPropertiesFbDoc) {
        appPropertiesFbService.save(appPropertiesFbDoc);
        return RouteUtils.redirectTo(AppPropertiesRoutes.EDIT);
    }
}
