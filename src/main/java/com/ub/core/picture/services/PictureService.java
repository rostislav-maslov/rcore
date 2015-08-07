package com.ub.core.picture.services;

import com.mongodb.gridfs.GridFSDBFile;
import com.ub.core.file.services.FileService;
import com.ub.core.picture.models.PictureDoc;
import com.ub.core.picture.models.PictureSize;
import com.ub.core.picture.view.all.SearchAdminRequest;
import com.ub.core.picture.view.all.SearchAdminResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@Component
public class PictureService {

    @Autowired private FileService fileService;
    @Autowired private MongoTemplate mongoTemplate;
    @Autowired private PictureColorService pictureColorService;

    public PictureDoc findById(ObjectId id) {
        return mongoTemplate.findById(id, PictureDoc.class);
    }

    public PictureDoc findByUrl(String url) {
        return mongoTemplate.findOne(new Query(Criteria.where("url").is(url)), PictureDoc.class);
    }

    public void delete(ObjectId objectId) {
        PictureDoc pictureDoc = findById(objectId);

        if (pictureDoc == null)
            return;

        fileService.delete(pictureDoc.getOriginFileId());
        for (PictureSize pictureSize : pictureDoc.getSizes().values()) {
            fileService.delete(pictureSize.getFileId());
        }

        mongoTemplate.remove(pictureDoc);
    }

    public PictureDoc addNewSizeToPicture(ObjectId pictureId, Integer width) throws IOException {
        PictureDoc pictureDoc = findById(pictureId);
        if (pictureDoc.getSizes() != null) {
            for (PictureSize pictureSize : pictureDoc.getSizes().values()) {
                if (pictureSize.getWidth().equals(width)) {
                    return pictureDoc;
                }
            }
        }

        GridFSDBFile gridFSDBFile = fileService.getFile(pictureDoc.getOriginFileId());
        InputStream is = gridFSDBFile.getInputStream();
        BufferedImage originalImage = ImageIO.read(is);
        InputStream newSize = resizeImage(is, width);

        //BufferedImage originalImage = ImageIO.read(newSize);
        GridFSDBFile newSizeFile = fileService.save(newSize);
        PictureSize pictureSize = new PictureSize();
        pictureSize.setFileId((ObjectId)newSizeFile.getId());
        pictureSize.setWidth(width);
        pictureSize.setHieght(originalImage.getHeight());
        pictureDoc.addSize(pictureSize);

        mongoTemplate.save(pictureDoc);

        return pictureDoc;
    }

    public InputStream resizeImage(InputStream is, int width) throws IOException {
        BufferedImage originalImage = ImageIO.read(is);

        int height = width * originalImage.getHeight() / originalImage.getWidth();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "png", os);
        image.flush();
        is.close();
        is = new ByteArrayInputStream(os.toByteArray());
        os.flush();
        os.close();
        return is;
    }

    public PictureSize getPictureSize(ObjectId fileId) {
        PictureSize pictureSize = new PictureSize();
        try {
            BufferedImage bufferedImage = ImageIO.read(fileService.getFile(fileId).getInputStream());

            pictureSize.setWidth(bufferedImage.getWidth());
            pictureSize.setHieght(bufferedImage.getHeight());
            pictureSize.setFileId(fileId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pictureSize;
    }

    public ObjectId saveWithDelete(MultipartFile file, ObjectId oldId) {
        if (file != null && file.getSize() != 0) {
            if (oldId != null) delete(oldId);

            PictureDoc pictureDoc = save(file);
            return pictureDoc.getId();
        }

        return oldId;
    }

    public PictureDoc save(MultipartFile multipartFile) {
        GridFSDBFile gridFSDBFile = fileService.save(multipartFile);
        if (gridFSDBFile == null) return null;

        PictureDoc pictureDoc = new PictureDoc();

        pictureDoc.setContentType(multipartFile.getContentType());
        pictureDoc.setFileName(multipartFile.getOriginalFilename());
        pictureDoc.setOriginFileId((ObjectId) gridFSDBFile.getId());
        pictureDoc.setUrl(multipartFile.getOriginalFilename());

        PictureSize pictureSize = getPictureSize(pictureDoc.getOriginFileId());
        pictureDoc.addSize(pictureSize);

        //Определение среднего цвета изображения
        try {
            pictureDoc.setColor(pictureColorService.getDominantColor(gridFSDBFile.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        mongoTemplate.save(pictureDoc);
        return pictureDoc;
    }

    public PictureDoc saveByUrl(String url) {
        try {
            InputStream str = new URL(url).openStream();
            return save(str);
        } catch (Exception e) {

        }
        return null;
    }

    public PictureDoc save(InputStream inputStream) {
        GridFSDBFile gridFSDBFile = fileService.save(inputStream);
        if (gridFSDBFile == null) return null;

        PictureDoc pictureDoc = new PictureDoc();

        pictureDoc.setContentType("");
        pictureDoc.setFileName("");
        pictureDoc.setOriginFileId((ObjectId) gridFSDBFile.getId());
        pictureDoc.setUrl("");

        PictureSize pictureSize = getPictureSize(pictureDoc.getOriginFileId());
        pictureDoc.addSize(pictureSize);

        //Определение среднего цвета изображения
        try {
            pictureDoc.setColor(pictureColorService.getDominantColor(gridFSDBFile.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        mongoTemplate.save(pictureDoc);
        return pictureDoc;
    }

    public SearchAdminResponse findAll(SearchAdminRequest searchAdminRequest) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(
                searchAdminRequest.getCurrentPage(),
                searchAdminRequest.getPageSize(),
                sort);

        Criteria criteria = new Criteria();

        Query query = new Query(criteria);
        Long count = mongoTemplate.count(query, PictureDoc.class);
        query = query.with(pageable);

        List<PictureDoc> result = mongoTemplate.find(query, PictureDoc.class);
        SearchAdminResponse searchAdminResponse = new SearchAdminResponse(
                searchAdminRequest.getCurrentPage(),
                searchAdminRequest.getPageSize(),
                result);
        searchAdminResponse.setAll(count);
        searchAdminResponse.setQuery(searchAdminRequest.getQuery());
        return searchAdminResponse;
    }


}
