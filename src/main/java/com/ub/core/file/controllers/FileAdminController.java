package com.ub.core.file.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.BindException;

@Controller
@RequestMapping(value = "/admin/files/")
public class FileAdminController {

    @RequestMapping(value = "add", method = RequestMethod.GET)
    protected String add(Model model) throws Exception {
        return "com.ub.core.admin.file.add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    protected void addPost(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "file")MultipartFile multipartFile,
                           BindException errors, Model model) throws Exception {

        //FileUploadView file = (FileUploadView) command;
        //MultipartFile multipartFile = file.getFile();
//        String fileName = "";
//        if (multipartFile != null) {
//            fileName = multipartFile.getOriginalFilename();
//            return multipartFile;
//        }
//        return null;

        try {
            // get your file as InputStream
            InputStream is = multipartFile.getInputStream();
            // copy it to response's OutputStream
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            //log.info("Error writing file to output stream. Filename was '" + fileName + "'");
            throw new RuntimeException("IOError writing file to output stream");
        }
    }
}
