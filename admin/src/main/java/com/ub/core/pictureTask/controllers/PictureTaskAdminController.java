package com.ub.core.pictureTask.controllers;

import com.mongodb.gridfs.GridFSDBFile;
import com.ub.core.pictureTask.models.PictureTaskDoc;
import com.ub.core.pictureTask.routes.PictureTaskAdminRoutes;
import com.ub.core.pictureTask.services.PictureTaskService;
import com.ub.core.pictureTask.views.all.SearchPictureTaskAdminRequest;
import com.ub.core.pictureTask.views.all.SearchPictureTaskAdminResponse;
import com.ub.core.base.utils.RouteUtils;
import com.ub.core.file.services.FileService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ub.core.base.views.breadcrumbs.BreadcrumbsLink;
import com.ub.core.base.views.pageHeader.PageHeader;

@Controller
public class PictureTaskAdminController {
    @Autowired private PictureTaskService pictureTaskService;

    private PageHeader defaultPageHeader(String current) {
        PageHeader pageHeader = PageHeader.defaultPageHeader();
        pageHeader.setLinkAdd(PictureTaskAdminRoutes.ADD);
        pageHeader.getBreadcrumbs().getLinks().add(new BreadcrumbsLink(PictureTaskAdminRoutes.ALL, "Все Picture Tasks"));
        pageHeader.getBreadcrumbs().setCurrentPageTitle(current);
        return pageHeader;
    }

    @RequestMapping(value = PictureTaskAdminRoutes.ADD, method = RequestMethod.GET)
    public String create(Model model) {
        PictureTaskDoc pictureTaskDoc = new PictureTaskDoc();
        pictureTaskDoc.setId(new ObjectId());
        model.addAttribute("pictureTaskDoc", pictureTaskDoc);
        model.addAttribute("pageHeader", defaultPageHeader("Добавление"));
        return "com.ub.core.admin.pictureTask.add";
    }

    @RequestMapping(value = PictureTaskAdminRoutes.ADD, method = RequestMethod.POST)
    public String create(@ModelAttribute PictureTaskDoc pictureTaskDoc,
                         RedirectAttributes redirectAttributes) {
        pictureTaskService.save(pictureTaskDoc);
        redirectAttributes.addAttribute("id", pictureTaskDoc.getId());
        return RouteUtils.redirectTo(PictureTaskAdminRoutes.EDIT);
    }

    @RequestMapping(value = PictureTaskAdminRoutes.EDIT, method = RequestMethod.GET)
    public String update(@RequestParam ObjectId id, Model model) {
        PictureTaskDoc pictureTaskDoc = pictureTaskService.findById(id);
        model.addAttribute("pictureTaskDoc", pictureTaskDoc);
        model.addAttribute("pageHeader", defaultPageHeader("Редактирование"));
        return "com.ub.core.admin.pictureTask.edit";
    }

    @RequestMapping(value = PictureTaskAdminRoutes.EDIT, method = RequestMethod.POST)
    public String update(@ModelAttribute PictureTaskDoc pictureTaskDoc,
                         RedirectAttributes redirectAttributes) {
        pictureTaskService.save(pictureTaskDoc);
        redirectAttributes.addAttribute("id", pictureTaskDoc.getId());
        return RouteUtils.redirectTo(PictureTaskAdminRoutes.EDIT);
    }

    @RequestMapping(value = PictureTaskAdminRoutes.ALL, method = RequestMethod.GET)
    public String all(@RequestParam(required = false, defaultValue = "0") Integer currentPage,
                      @RequestParam(required = false, defaultValue = "") String query,
                      Model model) {
        SearchPictureTaskAdminRequest searchPictureTaskAdminRequest = new SearchPictureTaskAdminRequest(currentPage);
        searchPictureTaskAdminRequest.setQuery(query);
        model.addAttribute("searchPictureTaskAdminResponse", pictureTaskService.findAll(searchPictureTaskAdminRequest));
        model.addAttribute("pageHeader", defaultPageHeader("Все"));
        return "com.ub.core.admin.pictureTask.all";
    }

    @RequestMapping(value = PictureTaskAdminRoutes.MODAL_PARENT, method = RequestMethod.GET)
    public String modalResponse(@RequestParam(required = false, defaultValue = "0") Integer currentPage,
                                @RequestParam(required = false, defaultValue = "") String query,
                                Model model) {
        SearchPictureTaskAdminRequest searchPictureTaskAdminRequest = new SearchPictureTaskAdminRequest(currentPage);
        searchPictureTaskAdminRequest.setQuery(query);
        model.addAttribute("searchPictureTaskAdminResponse", pictureTaskService.findAll(searchPictureTaskAdminRequest));
        return "com.ub.core.admin.pictureTask.modal.parent";
    }

    @RequestMapping(value = PictureTaskAdminRoutes.DELETE, method = RequestMethod.GET)
    public String delete(@RequestParam ObjectId id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("pageHeader", defaultPageHeader("Удаление"));
        return "com.ub.core.admin.pictureTask.delete";
    }

    @RequestMapping(value = PictureTaskAdminRoutes.DELETE, method = RequestMethod.POST)
    public String delete(@RequestParam ObjectId id) {
        pictureTaskService.remove(id);
        return RouteUtils.redirectTo(PictureTaskAdminRoutes.ALL);
    }
}
