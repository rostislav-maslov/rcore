package com.ub.core.seo.robotsCore.controllers;

import com.ub.core.seo.robotsCore.models.RobotsCoreDoc;
import com.ub.core.seo.robotsCore.routes.RobotsCoreAdminRoutes;
import com.ub.core.seo.robotsCore.services.RobotsCoreService;
import com.ub.core.base.utils.RouteUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RobotsCoreAdminController {
    @Autowired private RobotsCoreService robotsCoreService;

    @RequestMapping(value = RobotsCoreAdminRoutes.EDIT, method = RequestMethod.GET)
    public String update(Model model) {
        RobotsCoreDoc robotsCoreDoc = robotsCoreService.getRobots();
        model.addAttribute("robotsCoreDoc", robotsCoreDoc);
        return "com.ub.core.seo.admin.robotsCore.edit" ;
    }

    @RequestMapping(value = RobotsCoreAdminRoutes.EDIT, method = RequestMethod.POST)
    public String update(@RequestParam String robots,
                         RedirectAttributes redirectAttributes) {
        RobotsCoreDoc robotsCoreDoc = new RobotsCoreDoc();
        robotsCoreDoc.setRobots(robots);
        robotsCoreService.save(robotsCoreDoc);
        redirectAttributes.addAttribute("id",robotsCoreDoc.getId());
        return RouteUtils.redirectTo(RobotsCoreAdminRoutes.EDIT);
    }

}
