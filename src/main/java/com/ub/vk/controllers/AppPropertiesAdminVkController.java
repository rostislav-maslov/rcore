package com.ub.vk.controllers;

import com.ub.core.base.utils.RouteUtils;
import com.ub.vk.models.AppPropertiesVkDoc;
import com.ub.vk.routes.AppPropertiesRoutes;
import com.ub.vk.services.AppPropertiesVkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppPropertiesAdminVkController {
    @Autowired private AppPropertiesVkService appPropertiesVkService;

    @RequestMapping(value = AppPropertiesRoutes.EDIT, method = RequestMethod.GET)
    public String edit(Model model) {
        model.addAttribute("appPropertiesVkDoc", appPropertiesVkService.getVkProp());
        return "com.ub.vk.admin.appprop.edit";
    }

    @RequestMapping(value = AppPropertiesRoutes.EDIT, method = RequestMethod.POST)
    public String edit(@ModelAttribute AppPropertiesVkDoc appPropertiesVkDoc, Model model) {
        appPropertiesVkService.save(appPropertiesVkDoc);
        return RouteUtils.redirectTo(AppPropertiesRoutes.EDIT);
    }

}
