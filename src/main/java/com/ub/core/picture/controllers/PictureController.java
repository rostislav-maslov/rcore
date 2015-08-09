package com.ub.core.picture.controllers;

import com.mongodb.gridfs.GridFSDBFile;
import com.ub.core.base.utils.StringUtils;
import com.ub.core.file.services.FileService;
import com.ub.core.picture.models.PictureDoc;
import com.ub.core.picture.routes.PicturesRoutes;
import com.ub.core.picture.services.PictureService;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    protected void pics(HttpServletResponse response, @PathVariable(value = "id") String id,
                        @RequestParam(required = false) Integer width) {
        try {
            PictureDoc pictureDoc = null;
            try {
                ObjectId objectId = new ObjectId(id);
                pictureDoc = pictureService.findById(objectId);
            } catch (Exception e) {
            }
            if(pictureDoc == null){
                pictureDoc = pictureService.findByUrl(id);
            }
            if(pictureDoc == null){
                response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
                response.setHeader("Location", "/404");
            }

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

            if (width != null && width > 0) {
                InputStream newIs = pictureService.resizeImage(is, width);
                IOUtils.copy(newIs, response.getOutputStream());
                newIs.close();
                is.close();
//                IOUtils.copy(is, response.getOutputStream());
//                PictureSize pictureSize = pictureDoc.hasSizeWidth(width);
//                if(pictureSize != null) {
//                    GridFSDBFile newSizeGrid = fileService.getFile(pictureSize.getFileId());
//                    IOUtils.copy(newSizeGrid.getInputStream(), response.getOutputStream());
//                    newSizeGrid.getInputStream().close();
//                } else {
//                    InputStream newIs = pictureService.addNewSizeToPicture(is, pictureDoc, width);
//                    IOUtils.copy(newIs, response.getOutputStream());
//                    newIs.close();
//                }
            } else {
                IOUtils.copy(is, response.getOutputStream());
                is.close();
            }

            response.flushBuffer();
        } catch (IOException ex) {
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", "/404");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", "/404");
        }
    }



}
