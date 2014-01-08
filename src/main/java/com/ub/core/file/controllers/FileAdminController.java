package com.ub.core.file.controllers;

import com.mongodb.gridfs.GridFSDBFile;
import com.ub.core.file.FileRoutes;
import com.ub.core.file.FileTiles;
import com.ub.core.file.services.FileService;
import com.ub.core.file.store.FileInfo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.BindException;

@Controller
public class FileAdminController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = FileRoutes.ADD, method = RequestMethod.GET)
    protected String add(Model model) throws Exception {
        return FileTiles.ADD;
    }

    @RequestMapping(value = FileRoutes.ADD, method = RequestMethod.POST)
    protected String addPost(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "file") MultipartFile multipartFile,
                             BindException errors, Model model) throws Exception {
        try {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileMeta(multipartFile.getContentType());
            fileInfo.setFileName(multipartFile.getOriginalFilename());
            fileInfo.setInputStream(multipartFile.getInputStream());

            GridFSDBFile gridFSDBFile = fileService.save(fileInfo);
        } catch (IOException ex) {
            //log.info("Error writing file to output stream. Filename was '" + fileName + "'");
            throw new RuntimeException("IOError writing file to output stream");
        }
        return "redirect:"+FileRoutes.LIST;
    }

    @RequestMapping(value = FileRoutes.LIST, method = RequestMethod.GET)
    protected String list(Model model) {
        model.addAttribute("files", fileService.getAllView());
        return FileTiles.LIST;
    }

    @RequestMapping(value = FileRoutes.DELETE, method = RequestMethod.GET)
    protected String delete(@RequestParam(value = "id")String id, Model model) throws Exception {
        model.addAttribute("id", id);
        return FileTiles.DELETE;
    }

    @RequestMapping(value = FileRoutes.DELETE, method = RequestMethod.POST)
    protected String deletePost(@RequestParam(value = "id")String id, Model model) throws Exception {
        ObjectId objectId = new ObjectId(id);
        fileService.delete(objectId);
        return "redirect:"+FileRoutes.LIST;
    }
}
