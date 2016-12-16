package com.ub.core.picture.controllers;

import com.mongodb.gridfs.GridFSDBFile;
import com.ub.core.base.utils.StringUtils;
import com.ub.core.file.services.FileService;
import com.ub.core.picture.models.PictureDoc;
import com.ub.core.picture.models.PictureSize;
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

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.GregorianCalendar;

@Controller
public class PictureController {
    @Autowired private PictureService pictureService;

    @Autowired private FileService fileService;


    @RequestMapping(value = PicturesRoutes.PIC, method = RequestMethod.GET)
    protected void pics(HttpServletResponse response,
                        @PathVariable(value = "id") String id,
                        @RequestParam(required = false) Integer width) {
        try {
            PictureDoc pictureDoc = null;
            try {
                ObjectId objectId = new ObjectId(id);
                pictureDoc = pictureService.findById(objectId);
            } catch (Exception e) {
            }
            if (pictureDoc == null) {
                pictureDoc = pictureService.findByUrl(id);
            }
            if (pictureDoc == null) {
                response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
                response.setHeader("Location", "/404");
            }
            //Кеширование
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(new Date());
            gc.add(GregorianCalendar.YEAR, 1);
            long ex = gc.getTime().getTime();
            response.setDateHeader("Expires", ex);
            //конец кеширования

            GridFSDBFile gridFSDBFile = fileService.getFile(pictureDoc.getOriginFileId());
            response.setHeader("Content-Disposition", "filename=\"" + StringUtils.cyrillicToLatin(gridFSDBFile.getFilename()) + "\"");
            response.setContentType(gridFSDBFile.getContentType());
            if (width != null && width > 0) {
                PictureSize pictureSize = pictureService.getSizeFromPic(pictureDoc, width);
                GridFSDBFile gridFSDBFileWidth = fileService.getFile(pictureSize.getFileId());
                InputStream is = gridFSDBFileWidth.getInputStream();
                byte[] bt = IOUtils.toByteArray(is);
                response.setContentLength(bt.length);
//                IOUtils.copy(is, response.getOutputStream());
                response.getOutputStream().write(bt);
                is.close();
            } else {
                InputStream is = gridFSDBFile.getInputStream();
                byte[] bt = IOUtils.toByteArray(is);
                response.setContentLength(bt.length);
//                IOUtils.copy(is, response.getOutputStream());
                response.getOutputStream().write(bt);
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


    public static String PATH_TO_IMAGE_MAGIC = "/Users/maslov/workspace/kickcity/image-magic/ImageMagick-7.0.3" +
            ":/Users/maslov/workspace/kickcity/image-magic/ImageMagick-7.0.3/bin" +
            ":/Users/maslov/workspace/kickcity/image-magic/ImageMagick-7.0.3/etc" +
            ":/Users/maslov/workspace/kickcity/image-magic/ImageMagick-7.0.3/include" +
            ":/Users/maslov/workspace/kickcity/image-magic/ImageMagick-7.0.3/lib" +
            ":/Users/maslov/workspace/kickcity/image-magic/ImageMagick-7.0.3/share" +
            "";
    public static String PATH_TO_IMAGE_FOLDER = "/Users/maslov/workspace/kickcity/image-magic";
    public static String PATH_TO_IMAGE_FOLDER_ = "\\Users\\maslov\\workspace\\kickcity\\image-magic\\";
}
