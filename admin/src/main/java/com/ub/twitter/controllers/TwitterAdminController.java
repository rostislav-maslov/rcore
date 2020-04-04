package com.ub.twitter.controllers;

import com.ub.core.base.utils.RouteUtils;
import com.ub.twitter.models.TwitterAppPropertiesDoc;
import com.ub.twitter.routes.TwitterRoutes;
import com.ub.twitter.services.TwitterService;
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
public class TwitterAdminController {

    @Autowired private TwitterService twitterService;

    @RequestMapping(value = TwitterRoutes.EDIT, method = RequestMethod.GET)
    public String edit(Model model) {
        model.addAttribute("twitterAppPropertiesDoc", twitterService.getTwitter());
        return "com.ub.twitter.admin.appprop.edit";
    }

    @RequestMapping(value = TwitterRoutes.EDIT, method = RequestMethod.POST)
    public String edit(@ModelAttribute TwitterAppPropertiesDoc twitterAppPropertiesDoc, Model model) {
        twitterService.save(twitterAppPropertiesDoc);
        return RouteUtils.redirectTo(TwitterRoutes.EDIT);
    }
}
