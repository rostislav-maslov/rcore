package com.ub.core.picture.services;

import com.mongodb.gridfs.GridFSDBFile;
import com.ub.core.file.services.FileService;
import com.ub.core.picture.models.PictureDoc;
import com.ub.core.picture.models.PictureSize;
import com.ub.core.picture.view.all.SearchAdminRequest;
import com.ub.core.picture.view.all.SearchAdminResponse;
import org.bson.types.ObjectId;
import org.imgscalr.Scalr;
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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@Component
public class PictureService {
    public static final Integer LIMIT_OF_IMAGE_SIZES = 5;
    public static final Boolean RETURN_ORIGIN_IF_LIMIT = true;


    @Autowired private FileService fileService;
    @Autowired private MongoTemplate mongoTemplate;
    @Autowired private PictureColorService pictureColorService;

    public PictureDoc findById(ObjectId id) {
        return mongoTemplate.findById(id, PictureDoc.class);
    }

    public PictureDoc findByUrl(String url) {
        return mongoTemplate.findOne(new Query(Criteria.where("url").is(url)), PictureDoc.class);
    }

    public long countAll() {
        return mongoTemplate.count(new Query(), PictureDoc.class);
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

    public InputStream addNewSizeToPicture(InputStream is, PictureDoc pictureDoc, Integer width) throws IOException {
        if (pictureDoc.getSizes().size() >= LIMIT_OF_IMAGE_SIZES) {
            GridFSDBFile gridFSDBFile = fileService.getFile(pictureDoc.getOriginFileId());
            return gridFSDBFile.getInputStream();
        }

        InputStream newSize = resizeImage(is, width);

        GridFSDBFile newSizeFile = fileService.save(newSize);

        BufferedImage originalImage = ImageIO.read(newSizeFile.getInputStream());

        // сохраняем еще один ресайз в картинке
        PictureSize pictureSize = new PictureSize();
        pictureSize.setFileId((ObjectId) newSizeFile.getId());
        pictureSize.setWidth(width);
        pictureSize.setHieght(originalImage.getHeight());


        pictureDoc.addSize(pictureSize);
        mongoTemplate.save(pictureDoc);

        is.close();

        return newSizeFile.getInputStream();
    }

    public static synchronized InputStream resizeImage(InputStream is, int width) throws IOException {
        try {
            BufferedImage originalImage = ImageIO.read(is);
            BufferedImage thumbnail = Scalr.resize(originalImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, width);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(thumbnail, "png", os);
            thumbnail.flush();

            is.close();
            os.flush();
            os.close();
            originalImage.flush();
            System.gc();

            InputStream resultStream = new ByteArrayInputStream(os.toByteArray());
            return resultStream;
        } catch (IOException ex) {
            //http://stackoverflow.com/questions/2408613/unable-to-read-jpeg-image-using-imageio-readfile-file
            ex.printStackTrace();
            throw ex;
        }
    }

    public PictureSize getPictureSize(ObjectId fileId) {
        PictureSize pictureSize = new PictureSize();
        try {
            BufferedImage bufferedImage = ImageIO.read(fileService.getFile(fileId).getInputStream());
            if (bufferedImage != null) {
                pictureSize.setWidth(bufferedImage.getWidth());
                pictureSize.setHieght(bufferedImage.getHeight());
                pictureSize.setFileId(fileId);
            }
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
