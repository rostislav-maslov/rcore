package com.ub.core.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminTestController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome() {

        return "com.ub.core.client.main";
    }
}
