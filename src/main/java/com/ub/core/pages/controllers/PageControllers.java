package com.ub.core.pages.controllers;

import com.ub.core.pages.models.enums.PageStatus;
import com.ub.core.pages.routes.PagesClientTiles;
import com.ub.core.pages.services.PageService;
import com.ub.core.pages.views.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/page/")
public class PageControllers {
    @Autowired private PageService pageService;

    @RequestMapping(value = "{id}")
    public String page(@PathVariable String id, Model model){
        PageView pageView = pageService.getViewForClient(id);
        if(pageView == null || pageView.getStatus() == null ||!pageView.getStatus().equals(PageStatus.PUBLISHED.toString()))
            return "redirect:/";
        model.addAttribute("pageView", pageView);

        return PagesClientTiles.PAGE;
    }
}
