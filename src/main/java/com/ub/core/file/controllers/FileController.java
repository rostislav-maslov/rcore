package com.ub.core.file.controllers;

import com.mongodb.gridfs.GridFSDBFile;
import com.ub.core.base.utils.StringUtils;
import com.ub.core.file.FileRoutes;
import com.ub.core.file.services.FileService;
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
import java.util.Date;
import java.util.GregorianCalendar;

@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    @RequestMapping(value = FileRoutes.GET_FILE, method = RequestMethod.GET)
    protected void addPost(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable(value = FileRoutes.GET_FILE_FATH_VAR) String parPath) throws Exception {
        try {
            GridFSDBFile gridFSDBFile = fileService.getFile(new ObjectId(parPath));

//            response.setHeader("Cache-Control", "cache, store"); // HTTP 1.1.
//            response.setHeader("Pragma", "ache"); // HTTP 1.0.
//            response.setDateHeader("Expires", 5000); // Proxies.

            response.setHeader("Content-Disposition", "filename=\"" + StringUtils.cyrillicToLatin(gridFSDBFile.getFilename())+"\"");
            //Кеширование
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(new Date());
            gc.add(GregorianCalendar.HOUR_OF_DAY, 72);
            long ex = gc.getTime().getTime();
            response.setDateHeader("Expires",ex);
            //конец кеширования
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


    @RequestMapping(value = FileRoutes.GET_FILE_ATTACH, method = RequestMethod.GET)
    protected void fileAttach(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable(value = FileRoutes.GET_FILE_FATH_VAR) String parPath) throws Exception {
        try {
            GridFSDBFile gridFSDBFile = fileService.getFile(new ObjectId(parPath));

//            response.setHeader("Cache-Control", "cache, store"); // HTTP 1.1.
//            response.setHeader("Pragma", "ache"); // HTTP 1.0.
//            response.setDateHeader("Expires", 5000); // Proxies.

            response.setHeader("Content-Disposition", "attachment; filename=\"" + StringUtils.cyrillicToLatin(gridFSDBFile.getFilename())+"\"");
            //Кеширование
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(new Date());
            gc.add(GregorianCalendar.HOUR_OF_DAY, 72);
            long ex = gc.getTime().getTime();
            response.setDateHeader("Expires",ex);
            //конец кеширования
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
