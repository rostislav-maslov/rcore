package com.ub.core.user.controllers;

import com.ub.core.base.utils.RouteUtils;
import com.ub.core.role.models.RoleDoc;
import com.ub.core.role.roles.AvailableForRole;
import com.ub.core.role.service.RoleService;
import com.ub.core.security.annotationSecurity.AvailableForRoles;
import com.ub.core.user.routes.RoleAdminRoutes;
import com.ub.core.user.routes.UserAdminRoutes;
import com.ub.core.user.service.UserService;
import com.ub.core.user.views.AddEditRoleView;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Anton
 * Date: 1/17/14
 * Time: 7:58 AM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@AvailableForRoles(AvailableForRole.class)
public class RoleAdminController {

    @Autowired private UserService userService;
    @Autowired private RoleService roleService;
    // @Autowired private IUserDocService iUserDocService;

    @RequestMapping(value = RoleAdminRoutes.ADD, method = RequestMethod.GET)
    public String addRole(@RequestParam ObjectId id, ModelMap modelMap) {
        AddEditRoleView addEditRoleView = new AddEditRoleView();
        modelMap.addAttribute("roles", roleService.findAllRoles());
        modelMap.addAttribute("userId", id);
        return "com.ub.core.admin.role.addEdit";
    }

    @RequestMapping(value = RoleAdminRoutes.ADD, method = RequestMethod.POST)
    public String addRolePost(@RequestParam ObjectId user, @RequestParam String role, RedirectAttributes ra) {

        List<RoleDoc> roles = roleService.findAllRoles();

        for (RoleDoc r : roles) {
            if (r.getId().equals(role)) {
                userService.addRoleToUser(user, r);
                break;
            }
        }

        ra.addAttribute("id",user);
        return RouteUtils.redirectTo(UserAdminRoutes.EDIT);
    }

    @RequestMapping(value = RoleAdminRoutes.DELETE, method = RequestMethod.POST)
    public String deleteRole(@RequestParam ObjectId user, @RequestParam String role, RedirectAttributes ra) {
        List<RoleDoc> roles = roleService.findAllRoles();

        for (RoleDoc r : roles) {
            if (r.getId().equals(role)) {
                userService.deleteRoleToUser(user, r);
                break;
            }
        }

        ra.addAttribute("id", user);
        return RouteUtils.redirectTo(UserAdminRoutes.EDIT);
    }

}
