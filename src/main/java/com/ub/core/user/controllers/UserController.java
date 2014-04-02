package com.ub.core.user.controllers;

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

public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/verificate/{email}/{code}", method = RequestMethod.GET)
    public String verificateUser(@PathVariable String email, @PathVariable String code,  ModelMap modelMap){
        //TODO сделать верификацию
//        UserDoc userDoc = userService.getUserByEmail(email);
//        if(userDoc == null){
//            return "com.ub.bitcoin.client.verification.email.error";
//        }
//
//        if(userDoc.getUserDocStatuses().equals(UserDocStatuses.EMAIL_NOT_VERIFICATED)){
//            if(emailUserDoc.getVericationCode().equals(code)){
//                emailUserDoc.setUserDocStatuses(UserDocStatuses.EMAIL_VERIFICATED);
//                userService.updateEmailUser(emailUserDoc);
//                return "com.ub.bitcoin.client.verification.email.done";
//            }
//        }
//        else {
//            return "redirect:/user/settings";
//        }
        return "redirect:/";
    }
}
