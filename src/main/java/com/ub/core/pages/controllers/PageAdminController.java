package com.ub.core.pages.controllers;

import com.ub.core.pages.models.PageDoc;
import com.ub.core.pages.routes.PagesAdminRoutes;
import com.ub.core.pages.routes.PagesAdminTiles;
import com.ub.core.pages.services.PageService;
import com.ub.core.pages.views.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping()
public class PageAdminController {

    @Autowired private PageService pageService;

    @RequestMapping(value = PagesAdminRoutes.ALL, method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("pageList", pageService.getAllPagesForAdmin());
        return PagesAdminTiles.ALL;
    }

    @RequestMapping(value = PagesAdminRoutes.ADD, method = RequestMethod.GET)
    public String add(@RequestParam(value = "url",defaultValue = "")String url, Model modelMap){
        PageView pageView = pageService.getViewById(url);
        modelMap.addAttribute("pageView", pageView);

        modelMap.addAttribute("statuses", pageService.getAllStatuses());

        return PagesAdminTiles.ADD;
    }

    @RequestMapping(value = PagesAdminRoutes.ADD, method = RequestMethod.POST)
    public String addPost(@ModelAttribute @Valid PageView pageView, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return PagesAdminTiles.ADD;
        }
        pageService.update(pageView);

        return "redirect:" + PagesAdminRoutes.ALL;
    }

    @RequestMapping(value = PagesAdminRoutes.DELETE, method = RequestMethod.GET)
    protected String delete(@RequestParam(value = "id")String id, Model model) throws Exception {
        model.addAttribute("id", id);
        PageDoc pageDoc = pageService.getById(id);
        model.addAttribute("name", pageDoc.getTitle());
        return "com.ub.core.admin.pages.delete";
    }

    @RequestMapping(value = PagesAdminRoutes.DELETE, method = RequestMethod.POST)
    protected String deletePost(@RequestParam(value = "id")String id, Model model) throws Exception {
        PageDoc pageDoc = pageService.getById(id);
        pageService.delete(pageDoc);
        return "redirect:"+PagesAdminRoutes.ALL;
    }
}
