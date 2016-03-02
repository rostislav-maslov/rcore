package com.ub.core.file.services;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.ub.core.file.store.FileInfo;
import com.ub.core.file.view.FileView;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileService {
    public static final String IMG_CONTENT_TYPE = "image/jpeg";

    @Autowired
    private GridFsOperations gridFsTemplate;

    public GridFSDBFile getFile(ObjectId objectId) {
        List<GridFSDBFile> l = gridFsTemplate.find(new Query(Criteria.where("_id").is(objectId)).limit(1));
        if (l.size() > 0)
            return l.get(0);
        return null;
    }

    public ObjectId saveWithDelete(MultipartFile file, ObjectId oldId){
        if(file != null && file.getSize() != 0){
            if(oldId != null) delete(oldId);

            GridFSDBFile gridFSDBFile = save(file);
            return (ObjectId)gridFSDBFile.getId();
        }

        return oldId;
    }

    public ObjectId saveWithDelete(File file, ObjectId oldId){
        if(file != null && file.length() != 0){
            if(oldId != null) delete(oldId);

            GridFSDBFile gridFSDBFile = save(file);
            return (ObjectId)gridFSDBFile.getId();
        }

        return null;
    }

    public GridFSDBFile save(InputStream inputStream) {
        try {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileMeta("");
            fileInfo.setFileName("");
            fileInfo.setInputStream(inputStream);

            GridFSDBFile gridFSDBFile = save(fileInfo);
            return gridFSDBFile;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public GridFSDBFile save(InputStream inputStream,String fileMeta, String fileName) {
        try {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileMeta(fileMeta);
            fileInfo.setFileName(fileName);
            fileInfo.setInputStream(inputStream);

            GridFSDBFile gridFSDBFile = save(fileInfo);
            return gridFSDBFile;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public GridFSDBFile save(MultipartFile multipartFile) {
        try {
            if(multipartFile.getSize() == 0) return null;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileMeta(multipartFile.getContentType());
            fileInfo.setFileName(multipartFile.getOriginalFilename());
            fileInfo.setInputStream(multipartFile.getInputStream());

            GridFSDBFile gridFSDBFile = save(fileInfo);
            return gridFSDBFile;
        } catch (IOException ex) {
            //log.info("Error writing file to output stream. Filename was '" + fileName + "'");

        }
        return null;
    }

    public GridFSDBFile save(File file) {
        try {
            if(file.length() == 0) return null;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileMeta(IMG_CONTENT_TYPE);
            fileInfo.setFileName(file.getName());
            InputStream inputStream = new FileInputStream(file);
            fileInfo.setInputStream(inputStream);

            GridFSDBFile gridFSDBFile = save(fileInfo);
            return gridFSDBFile;
        } catch (IOException ex) {
            //log.info("Error writing file to output stream. Filename was '" + fileName + "'");

        }
        return null;
    }

    public GridFSDBFile save(FileInfo filePath) {
        InputStream inputStream = null;
        try {
            inputStream = filePath.getInputStream();
            GridFSFile gridFSFile = gridFsTemplate.store(inputStream, filePath.getFileName(), filePath.getFileMeta(), filePath.getMetaData());
            return getFile((ObjectId) gridFSFile.getId());
        } catch (Exception e) {
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public List<GridFSDBFile> getAll() {
        return gridFsTemplate.find(new Query(new Criteria()).with(new Sort(Sort.Direction.DESC, "_id")));
    }

    private FileView getView(GridFSDBFile gridFSDBFile){
        FileView fileView = new FileView();
        fileView.setId(gridFSDBFile.getId().toString());
        fileView.setName(gridFSDBFile.getFilename());
        return fileView;
    }

    public List<FileView> getAllView(){
        List<FileView> fileViews = new ArrayList<FileView>();
        for(GridFSDBFile gridFSDBFile : getAll()){
            fileViews.add(getView(gridFSDBFile));
        }
        return fileViews;
    }

    public void delete(ObjectId objectId) {
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(objectId)).limit(1));
    }
}
