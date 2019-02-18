package com.ub.core.picture.services;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.mongodb.gridfs.GridFSDBFile;
import com.ub.core.file.services.FileService;
import com.ub.core.picture.models.PictureDoc;
import com.ub.core.picture.models.PictureSize;
import com.ub.core.picture.view.all.SearchAdminRequest;
import com.ub.core.picture.view.all.SearchAdminResponse;
import com.ub.core.pictureTask.services.PictureTaskService;
import org.apache.commons.io.FilenameUtils;
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
import java.io.*;
import java.net.URL;
import java.util.List;

import static org.imgscalr.Scalr.Rotation;

@Component
public class PictureService {
    public static final Integer LIMIT_OF_IMAGE_SIZES = 5;
    public static final Boolean RETURN_ORIGIN_IF_LIMIT = true;


    @Autowired private FileService fileService;
    @Autowired private MongoTemplate mongoTemplate;
    @Autowired private PictureColorService pictureColorService;
    @Autowired private PictureTaskService pictureTaskService;

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

        try {
            fileService.delete(pictureDoc.getOriginFileId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (pictureDoc.getSizes() != null) {
            for (PictureSize pictureSize : pictureDoc.getSizes().values()) {
                try {
                    fileService.delete(pictureSize.getFileId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        mongoTemplate.remove(pictureDoc);
    }

    public PictureSize getSizeFromPic(PictureDoc pictureDoc, Integer width) throws IOException {
        for (PictureSize pictureSize : pictureDoc.getSizes().values()) {
            if (pictureSize.getWidth().equals(width) && pictureSize.getPictureSizeType() != null) {
                return pictureSize;
            }
        }

        PictureSize pictureSize = addNewSizeToPicture(pictureDoc, width);

        return pictureSize;
    }

    public PictureSize addNewSizeToPicture(PictureDoc pictureDoc, Integer width) throws IOException {
        if (pictureDoc.getSizes().size() >= LIMIT_OF_IMAGE_SIZES) {
            PictureSize pictureSize = new PictureSize();
            pictureSize.setFileId(pictureDoc.getOriginFileId());
            return pictureSize;
        }

        pictureTaskService.addTask(pictureDoc, width);

        PictureSize pictureSize = new PictureSize();
        pictureSize.setFileId(pictureDoc.getOriginFileId());
        return pictureSize;
    }

    @Deprecated
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
        GridFSDBFile gridFSDBFile;

        Rotation rotation = getPictureOrientation(multipartFile);
        if (rotation != null) {
            try {
                InputStream is = pictureIn(ImageIO.read(
                        multipartFile.getInputStream()),
                        FilenameUtils.getExtension(multipartFile.getOriginalFilename()),
                        rotation
                );
                gridFSDBFile = fileService.save(is);
            } catch (IOException e) {
                e.printStackTrace();

                return null;
            }
        } else {
            gridFSDBFile = fileService.save(multipartFile);
        }

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

    private Rotation getPictureOrientation(MultipartFile multipartFile) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(multipartFile.getInputStream());
            ExifIFD0Directory exifIFD0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
            if (exifIFD0Directory == null) {
                return null;
            }
            int orientation = exifIFD0Directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
            switch (orientation) {
                case 1: // [Exif IFD0] Orientation - Top, left side (Horizontal / normal)
                    return null;
                case 6: // [Exif IFD0] Orientation - Right side, top (Rotate 90 CW)
                    return Rotation.CW_90;
                case 3: // [Exif IFD0] Orientation - Bottom, right side (Rotate 180)
                    return Rotation.CW_180;
                case 8: // [Exif IFD0] Orientation - Left side, bottom (Rotate 270 CW)
                    return Rotation.CW_270;
            }
        } catch (ImageProcessingException | IOException | MetadataException e) {
            e.printStackTrace();
        }

        return null;
    }

    private InputStream pictureIn(BufferedImage image, String fileType, Rotation rotation) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(Scalr.rotate(image, rotation), fileType, os);

            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

        Criteria criteria = Criteria.where("fileName").regex(searchAdminRequest.getQuery(), "i");

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
