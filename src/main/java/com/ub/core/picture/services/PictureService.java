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
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.List;

@Component
public class PictureService {

    @Autowired private FileService fileService;
    @Autowired private MongoTemplate mongoTemplate;
    @Autowired private PictureColorService pictureColorService;

    public PictureDoc findById(ObjectId id) {
        return mongoTemplate.findById(id, PictureDoc.class);
    }

    public void delete(ObjectId objectId) {
        PictureDoc pictureDoc = findById(objectId);

        if(pictureDoc == null)
            return;

        fileService.delete(pictureDoc.getOriginFileId());
        for (PictureSize pictureSize : pictureDoc.getSizes().values()) {
            fileService.delete(pictureSize.getFileId());
        }

        mongoTemplate.remove(pictureDoc);
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
        searchAdminResponse.setAll(count.intValue());
        searchAdminResponse.setQuery(searchAdminRequest.getQuery());
        return searchAdminResponse;
    }


}
