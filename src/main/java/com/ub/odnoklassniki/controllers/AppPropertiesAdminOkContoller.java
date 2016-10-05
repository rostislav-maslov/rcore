package com.ub.odnoklassniki.controllers;

import com.ub.core.base.utils.RouteUtils;
import com.ub.odnoklassniki.models.AppPropertiesOkDoc;
import com.ub.odnoklassniki.routes.AppPropertiesOkRoutes;
import com.ub.odnoklassniki.services.AppPropertiesOkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppPropertiesAdminOkContoller {

    @Autowired private AppPropertiesOkService appPropertiesOkService;

    @RequestMapping(value = AppPropertiesOkRoutes.EDIT, method = RequestMethod.GET)
    public String edit(Model model) {
        model.addAttribute("appPropertiesOkDoc", appPropertiesOkService.getOkProp());
        return "com.ub.ok.admin.appprop.edit";
    }

    @RequestMapping(value = AppPropertiesOkRoutes.EDIT, method = RequestMethod.POST)
    public String edit(@ModelAttribute AppPropertiesOkDoc appPropertiesOkDoc) {
        appPropertiesOkService.save(appPropertiesOkDoc);
        return RouteUtils.redirectTo(AppPropertiesOkRoutes.EDIT);
    }
}
