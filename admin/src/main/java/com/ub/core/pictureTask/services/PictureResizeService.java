package com.ub.core.pictureTask.services;

import com.mongodb.gridfs.GridFSDBFile;
import com.ub.core.base.utils.StringUtils;
import com.ub.core.file.services.FileService;
import com.ub.core.picture.models.PictureDoc;
import com.ub.core.picture.models.PictureSize;
import com.ub.core.picture.models.PictureSizeType;
import com.ub.core.picture.services.PictureService;
import com.ub.core.pictureTask.models.PictureTaskDoc;
import com.ub.core.pictureTask.models.PictureTaskStatus;
import org.bson.types.ObjectId;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Component
public class PictureResizeService {

    @Autowired private PictureService pictureService;
    @Autowired private PictureTaskService pictureTaskService;
    @Autowired private FileService fileService;
    @Autowired private MongoTemplate mongoTemplate;

    public void runResizeForNextTask() {
        PictureTaskDoc pictureTaskDoc = pictureTaskService.getNextTask();
        if (pictureTaskDoc == null) {
            return;
        }

        try {
            runResize(pictureTaskDoc);
        } catch (Exception e) {
            pictureTaskDoc.setStatus(PictureTaskStatus.ERROR);
            pictureTaskDoc.setExc(e.getMessage());
            pictureTaskService.save(pictureTaskDoc);
        }
    }

    private void runResize(PictureTaskDoc pictureTaskDoc) throws IOException, IM4JavaException, InterruptedException {
        PictureDoc pictureDoc = pictureService.findById(pictureTaskDoc.getPictureId());
        cleanPictures(pictureDoc);

        GridFSDBFile gridFSDBFile = fileService.getFile(pictureDoc.getOriginFileId());

        File fileIn = new File(StringUtils.cyrillicToLatin(gridFSDBFile.getFilename()));
        File fileOut = new File(pictureTaskDoc.getWidth().toString() + "-" + fileIn.getName());
        Files.copy(gridFSDBFile.getInputStream(),fileIn.toPath(), StandardCopyOption.REPLACE_EXISTING);

        ConvertCmd convertCmd = new ConvertCmd();
        IMOperation op = new IMOperation();
        op.addImage(fileIn.getAbsolutePath());
        op.resize(pictureTaskDoc.getWidth());
        op.addImage(fileOut.getAbsolutePath());
        convertCmd.run(op);


        GridFSDBFile gridResult = fileService.save(fileOut);

        BufferedImage originalImage = ImageIO.read(gridResult.getInputStream());
        // сохраняем еще один ресайз в картинке
        PictureSize pictureSize = new PictureSize();
        pictureSize.setFileId((ObjectId) gridResult.getId());
        pictureSize.setWidth(originalImage.getWidth());
        pictureSize.setHieght(originalImage.getHeight());
        pictureSize.setPictureSizeType(PictureSizeType.IMAGE_MAGIC);

        pictureDoc.addSize(pictureSize);
        mongoTemplate.save(pictureDoc);

        fileIn.delete();
        fileOut.delete();

        originalImage.flush();

        pictureTaskDoc.setStatus(PictureTaskStatus.SUCCESS);
        pictureTaskService.save(pictureTaskDoc);
    }

    private void cleanPictures(PictureDoc pictureDoc) {
        List<String> toDelete = new ArrayList<>();

        for (String key : pictureDoc.getSizes().keySet()) {
            PictureSize pictureSize = pictureDoc.getSizes().get(key);
            if (pictureSize.getPictureSizeType() == null) {
                toDelete.add(key);

                if(pictureSize.getFileId().equals(pictureDoc.getOriginFileId()) == false) {
                    fileService.delete(pictureSize.getFileId());
                }
            }
        }

        for (String key : toDelete) {
            pictureDoc.getSizes().remove(key);
        }

        if (toDelete.size() > 0) {
            mongoTemplate.save(pictureDoc);
        }
    }

}
