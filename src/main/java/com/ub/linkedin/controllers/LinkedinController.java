package com.ub.linkedin.controllers;

import com.ub.core.base.utils.RouteUtils;
import com.ub.linkedin.models.AppPropertiesLinkedinDoc;
import com.ub.linkedin.models.LinkedAccessTokenResponse;
import com.ub.linkedin.routes.LinkedinRoutes;
import com.ub.linkedin.services.LinkedinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Eduard on 02.10.2015.
 */
@Controller
public class LinkedinController {
    @Autowired private LinkedinService service;

    @RequestMapping(value = LinkedinRoutes.EDIT, method = RequestMethod.GET)
    public String edit(Model model) {
        model.addAttribute("appPropertiesLinkedinDoc", service.getLinkedProperties());
        return "com.ub.linkedin.admin.appprop.edit";
    }

    @RequestMapping(value = LinkedinRoutes.EDIT, method = RequestMethod.POST)
    public String edit(@ModelAttribute AppPropertiesLinkedinDoc appPropertiesLinkedinDoc,
                       Model model) {
        service.save(appPropertiesLinkedinDoc);
        return RouteUtils.redirectTo(LinkedinRoutes.EDIT);
    }

    @ResponseBody
    @RequestMapping(value = "/linkedin/auth", method = RequestMethod.GET)
    public String auth() {
        return service.formAuthUri();
    }

    @ResponseBody
    @RequestMapping(value = "/linkedin/resp", method = RequestMethod.GET)
    public String auth(@RequestParam String code, @RequestParam String state) {
        try {
            LinkedAccessTokenResponse linkedAccessTokenResponse=service.getAccessToken(code,state);
            service.getUserInfo(linkedAccessTokenResponse.getAccess_token());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return service.formAuthUri();
    }
}
