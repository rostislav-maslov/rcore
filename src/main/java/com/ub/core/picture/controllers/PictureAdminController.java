package com.ub.core.picture.controllers;

import com.ub.core.base.utils.RouteUtils;
import com.ub.core.pages.routes.PagesAdminRoutes;
import com.ub.core.picture.routes.PicturesAdminRoutes;
import com.ub.core.picture.services.PictureService;
import com.ub.core.picture.view.all.SearchAdminRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PictureAdminController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = PicturesAdminRoutes.ADD, method = RequestMethod.GET)
    protected String add(Model model) throws Exception {
        return "com.ub.core.admin.pictures.add";
    }

    @RequestMapping(value = PicturesAdminRoutes.ADD, method = RequestMethod.POST)
    protected String add(@RequestParam(value = "file") MultipartFile multipartFile) {
        pictureService.save(multipartFile);

        return RouteUtils.redirectTo(PicturesAdminRoutes.ALL);
    }

    @RequestMapping(value = PicturesAdminRoutes.ALL, method = RequestMethod.GET)
    protected String all(@RequestParam(required = false, defaultValue = "0") Integer currentPage,
                         @RequestParam(required = false, defaultValue = "") String query,
                         Model model) {
        SearchAdminRequest searchAdminRequest = new SearchAdminRequest(currentPage);
        searchAdminRequest.setQuery(query);
        model.addAttribute("searchAdminResponse", pictureService.findAll(searchAdminRequest));
        return "com.ub.core.admin.pictures.all";
    }

    @RequestMapping(value = PicturesAdminRoutes.DELETE, method = RequestMethod.GET)
    protected String delete(@RequestParam ObjectId id, Model model) {
        model.addAttribute("id", id);
        return "com.ub.core.admin.pictures.delete";
    }

    @RequestMapping(value = PicturesAdminRoutes.DELETE, method = RequestMethod.POST)
    protected String delete(@RequestParam ObjectId id) {
        pictureService.delete(id);
        return RouteUtils.redirectTo(PagesAdminRoutes.ALL);
    }
}
