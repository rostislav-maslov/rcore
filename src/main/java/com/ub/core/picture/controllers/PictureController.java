package com.ub.core.picture.controllers;

import com.mongodb.gridfs.GridFSDBFile;
import com.ub.core.file.services.FileService;
import com.ub.core.picture.models.PictureDoc;
import com.ub.core.picture.routes.PicturesRoutes;
import com.ub.core.picture.services.PictureService;
import com.ub.core.utils.StringUtils;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.GregorianCalendar;

@Controller
public class PictureController {
    @Autowired private PictureService pictureService;

    @Autowired private FileService fileService;

    @RequestMapping(value = PicturesRoutes.PIC, method = RequestMethod.GET)
    protected void addPost(HttpServletResponse response, @PathVariable ObjectId id) {
        try {
            PictureDoc pictureDoc = pictureService.findById(id);

            GridFSDBFile gridFSDBFile = fileService.getFile(pictureDoc.getOriginFileId());
            response.setHeader("Content-Disposition", "filename=\"" + StringUtils.cyrillicToLatin(gridFSDBFile.getFilename()) + "\"");

            //Кеширование
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(new Date());
            gc.add(GregorianCalendar.HOUR_OF_DAY, 72);
            long ex = gc.getTime().getTime();
            response.setDateHeader("Expires", ex);
            //конец кеширования
            response.setContentType(gridFSDBFile.getContentType());
            InputStream is = gridFSDBFile.getInputStream();
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
            is.close();
        } catch (IOException ex) {
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", "/404");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", "/404");
        }
    }

}
