package com.ub.core.user.controllers;

import com.ub.core.user.models.EmailUserDoc;
import com.ub.core.user.models.UserDocStatuses;
import com.ub.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: Anton
 * Date: 1/22/14
 * Time: 1:03 AM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/verificate/{email}/{code}", method = RequestMethod.GET)
    public String verificateUser(@PathVariable String email, @PathVariable String code,  ModelMap modelMap){
        EmailUserDoc emailUserDoc = userService.getUserByEmail(email);
        if(emailUserDoc == null){
            return "redirect:/";
        }
        if(emailUserDoc.getUserDocStatuses().equals(UserDocStatuses.EMAIL_NOT_VERIFICATED)){
            if(emailUserDoc.getVericationCode().equals(code)){
                emailUserDoc.setUserDocStatuses(UserDocStatuses.EMAIL_VERIFICATED);
                //TODO: поставить сюда страницу с информацией что email верифицирован
                return "redirect: /";

            }
        }
        else {
            return "redirect: /";

        }


        return "";
    }
}
