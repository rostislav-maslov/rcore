package com.ub.core.base.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    protected String main(Model model) throws Exception {

        return "com.ub.core.admin.layout";
    }

}
