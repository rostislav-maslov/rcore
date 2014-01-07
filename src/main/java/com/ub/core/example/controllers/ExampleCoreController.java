package com.ub.core.example.controllers;

import com.ub.core.base.views.client.MainView;
import com.ub.core.menu.form.MenuForm;
import com.ub.core.menu.models.MenuDoc;
import com.ub.core.base.utils.ClassMapping;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/com/ub/core/controllers/ExampleCoreController/")
public class ExampleCoreController {
    private void initMainModel(Model model){
        MainView mainView = new MainView();
        model.addAttribute("view", mainView);
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test(Model model){
        MenuDoc menuDoc = new MenuDoc();
        menuDoc.setId(new ObjectId());
        menuDoc.setName("tetet");
        menuDoc.setUrl("sdvsdv");
        menuDoc.setParent(new MenuDoc());

        MenuForm menuForm = new MenuForm();
        menuForm.setId("sdfsd");
        menuForm.setName("sdf dsf ");
        menuForm.setParent("dsf sd fsd ");
        menuForm.setUrl("sdfsd");
        ClassMapping<MenuForm, MenuDoc> classMapping = new ClassMapping(MenuForm.class, MenuDoc.class);
        classMapping.mapping(menuForm,menuDoc);

        return "";
    }

    @RequestMapping(value = "carousel", method = RequestMethod.GET)
    public String carousel(Model model){
        initMainModel(model);
        return "com.ub.core.client.examples.carousel";
    }
    @RequestMapping(value = "grid", method = RequestMethod.GET)
    public String grid(Model model){
        initMainModel(model);
        return "com.ub.core.client.examples.grid";
    }
    @RequestMapping(value = "jumbotron", method = RequestMethod.GET)
    public String jumbotron(Model model){
        initMainModel(model);
        return "com.ub.core.client.examples.jumbotron";
    }
    @RequestMapping(value = "jumbotronnarrow", method = RequestMethod.GET)
    public String jumbotronnarrow(Model model){
        initMainModel(model);
        return "com.ub.core.client.examples.jumbotronnarrow";
    }
    @RequestMapping(value = "justifiednav", method = RequestMethod.GET)
    public String justifiednav(Model model){
        initMainModel(model);
        return "com.ub.core.client.examples.justifiednav";
    }
    @RequestMapping(value = "navbar", method = RequestMethod.GET)
    public String navbar(Model model){
        initMainModel(model);
        return "com.ub.core.client.examples.navbar";
    }
    @RequestMapping(value = "navbarfixedtop", method = RequestMethod.GET)
    public String navbarfixedtop(Model model){
        initMainModel(model);
        return "com.ub.core.client.examples.navbarfixedtop";
    }
    @RequestMapping(value = "navbarstatictop", method = RequestMethod.GET)
    public String navbarstatictop(Model model){
        initMainModel(model);
        return "com.ub.core.client.examples.navbarstatictop";
    }
    @RequestMapping(value = "nonresponse", method = RequestMethod.GET)
    public String nonresponse(Model model){
        initMainModel(model);
        return "com.ub.core.client.examples.nonresponse";
    }
    @RequestMapping(value = "offcanvas", method = RequestMethod.GET)
    public String offcanvas(Model model){
        initMainModel(model);
        return "com.ub.core.client.examples.offcanvas";
    }
    @RequestMapping(value = "signin", method = RequestMethod.GET)
    public String signin(Model model){
        initMainModel(model);
        return "com.ub.core.client.examples.signin";
    }
    @RequestMapping(value = "startertemplate", method = RequestMethod.GET)
    public String startertemplate(Model model){
        initMainModel(model);
        return "com.ub.core.client.examples.startertemplate";
    }
    @RequestMapping(value = "stickyfooter", method = RequestMethod.GET)
    public String stickyfooter(Model model){
        initMainModel(model);
        return "com.ub.core.client.examples.stickyfooter";
    }
    @RequestMapping(value = "stickyfooternavbar", method = RequestMethod.GET)
    public String stickyfooternavbar(Model model){
        initMainModel(model);
        return "com.ub.core.client.examples.stickyfooternavbar";
    }
    @RequestMapping(value = "theme", method = RequestMethod.GET)
    public String theme(Model model){
        initMainModel(model);
        return "com.ub.core.client.examples.theme";
    }
}
