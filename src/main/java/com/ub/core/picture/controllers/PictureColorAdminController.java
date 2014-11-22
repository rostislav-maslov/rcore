package com.ub.core.picture.controllers;

import com.ub.core.base.utils.RouteUtils;
import com.ub.core.picture.models.PictureColorDoc;
import com.ub.core.picture.routes.PictureColorAdminRoutes;
import com.ub.core.picture.services.IPictureColorService;
import com.ub.core.picture.services.PictureColorService;
import com.ub.core.picture.view.colors.SearchAdminRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PictureColorAdminController {
    @Autowired private IPictureColorService iPictureColorService;
    @Autowired private PictureColorService pictureColorService;


    @RequestMapping(value = PictureColorAdminRoutes.ADD, method = RequestMethod.GET)
    public String create(Model model) {
        PictureColorDoc pictureColorDoc = new PictureColorDoc();
        pictureColorDoc.setId(new ObjectId());
        model.addAttribute("pictureColorDoc", pictureColorDoc);
        return "com.ub.shop.admin.picture.color.add";
    }

    @RequestMapping(value = PictureColorAdminRoutes.ADD, method = RequestMethod.POST)
    public String create(@ModelAttribute PictureColorDoc pictureColorDoc,
                         RedirectAttributes redirectAttributes) {
        iPictureColorService.save(pictureColorDoc);
        redirectAttributes.addAttribute("id", pictureColorDoc.getId());
        return RouteUtils.redirectTo(PictureColorAdminRoutes.EDIT);
    }

    @RequestMapping(value = PictureColorAdminRoutes.EDIT, method = RequestMethod.GET)
    public String update(@RequestParam ObjectId id, Model model) {
        PictureColorDoc PictureColorDoc = iPictureColorService.findOne(id);
        model.addAttribute("pictureColorDoc", PictureColorDoc);
        return "com.ub.shop.admin.picture.color.edit";
    }

    @RequestMapping(value = PictureColorAdminRoutes.EDIT, method = RequestMethod.POST)
    public String update(@ModelAttribute PictureColorDoc PictureColorDoc,
                         RedirectAttributes redirectAttributes, Model model) {
        iPictureColorService.save(PictureColorDoc);
        redirectAttributes.addAttribute("id", PictureColorDoc.getId());
        return RouteUtils.redirectTo(PictureColorAdminRoutes.EDIT);
    }

    @RequestMapping(value = PictureColorAdminRoutes.ALL, method = RequestMethod.GET)
    public String all(@RequestParam(required = false, defaultValue = "0") Integer currentPage,
                      @RequestParam(required = false, defaultValue = "") String query,
                      Model model) {
        SearchAdminRequest searchAdminRequest = new SearchAdminRequest(currentPage);
        searchAdminRequest.setQuery(query);
        model.addAttribute("searchAdminRequest", pictureColorService.findAll(searchAdminRequest));
        return "com.ub.shop.admin.picture.color.all";
    }

    @RequestMapping(value = PictureColorAdminRoutes.DELETE, method = RequestMethod.GET)
    public String delete(@RequestParam ObjectId id, Model model) {
        model.addAttribute("id", id);
        return "com.ub.shop.admin.picture.color.delete";
    }

    @RequestMapping(value = PictureColorAdminRoutes.DELETE, method = RequestMethod.POST)
    public String delete(@RequestParam ObjectId id) {
        iPictureColorService.delete(id);
        return RouteUtils.redirectTo(PictureColorAdminRoutes.ALL);
    }
}
