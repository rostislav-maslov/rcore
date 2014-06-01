package com.ub.core.file.controllers;

import com.mongodb.gridfs.GridFSDBFile;
import com.ub.core.file.FileRoutes;
import com.ub.core.file.services.FileService;
import com.ub.core.utils.StringUtils;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    @RequestMapping(value = FileRoutes.GET_FILE, method = RequestMethod.GET)
    protected void addPost(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable(value = FileRoutes.GET_FILE_FATH_VAR) String parPath) throws Exception {
        try {
            GridFSDBFile gridFSDBFile = fileService.getFile(new ObjectId(parPath));
            response.setHeader("Content-Disposition", "filename=\"" + StringUtils.cyrillicToLatin(gridFSDBFile.getFilename())+"\"");
            response.setContentType(gridFSDBFile.getContentType());
            InputStream is = gridFSDBFile.getInputStream();
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
            is.close();
        } catch (IOException ex) {
            //log.info("Error writing file to output stream. Filename was '" + fileName + "'");
            //throw new RuntimeException("IOError writing file to output stream");
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", "/404");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", "/404");
        }
    }

}
