package com.ub.core.user.controllers;

import com.ub.core.base.httpResponse.ResourceNotFoundException;
import com.ub.core.base.role.BaseAdminRole;
import com.ub.core.base.role.SuperUser;
import com.ub.core.base.routes.BaseRoutes;
import com.ub.core.base.utils.RouteUtils;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.routes.UserAdminRoutes;
import com.ub.core.user.service.UserService;
import com.ub.core.user.service.exceptions.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class InitAdminPanelController {

    @Autowired private UserService userService;

    @RequestMapping(value = UserAdminRoutes.INIT_USER, method = RequestMethod.GET)
    public String init() {
        ArrayList<UserDoc> all = userService.getAllUsers();
        if(all != null && all.size()>0){
            throw new ResourceNotFoundException();
        }
        return "com.ub.core.admin.user.init";
    }

    @RequestMapping(value = UserAdminRoutes.INIT_USER, method = RequestMethod.POST)
    public String init(@RequestParam String email, @RequestParam String password) throws UserExistException{
        ArrayList<UserDoc> all = userService.getAllUsers();
        if(all != null && all.size()>0){
            throw new ResourceNotFoundException();
        }

        UserDoc userDoc = new UserDoc();
        userDoc.setEmail(email);
        userDoc.setPasswordAsHex(password);
        userDoc = userService.createUserByEmail(email, password);
        userDoc.getRoles().add(new BaseAdminRole());
        userDoc.getRoles().add(new SuperUser());
        userService.save(userDoc);
        return RouteUtils.redirectTo(BaseRoutes.ADMIN);
    }

}
