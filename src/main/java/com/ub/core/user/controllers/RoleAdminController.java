package com.ub.core.user.controllers;

import com.ub.core.base.role.Role;
import com.ub.core.base.role.RoleBoost;
import com.ub.core.base.utils.RouteUtils;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.routes.RoleAdminRoutes;
import com.ub.core.user.routes.UserAdminRoutes;
import com.ub.core.user.service.IUserDocService;
import com.ub.core.user.service.UserService;
import com.ub.core.user.views.AddEditRoleView;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Anton
 * Date: 1/17/14
 * Time: 7:58 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller

public class RoleAdminController {


    @Autowired private UserService userService;
    @Autowired private IUserDocService iUserDocService;

    @RequestMapping(value = RoleAdminRoutes.ADD, method = RequestMethod.GET)
    public String addRole(@RequestParam ObjectId id, ModelMap modelMap) {
        AddEditRoleView addEditRoleView = new AddEditRoleView();
        modelMap.addAttribute("roles", RoleBoost.allRoles());
        modelMap.addAttribute("userId", id);
        return "com.ub.core.admin.role.addEdit";
    }

    @RequestMapping(value = RoleAdminRoutes.ADD, method = RequestMethod.POST)
    public String addRolePost(@RequestParam ObjectId user, @RequestParam String role, ModelMap modelMap) {

        List<Role> roles = RoleBoost.allRoles();

        for (Role r : roles) {
            if (r.getId().equals(role)) {
                UserDoc userDoc = userService.getUser(user);
                userDoc.getRoles().add(r);
                iUserDocService.save(userDoc);
                break;
            }
        }

        return RouteUtils.redirectTo(UserAdminRoutes.LIST);
    }


}
