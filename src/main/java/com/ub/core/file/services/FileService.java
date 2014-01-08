package com.ub.core.file.services;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.ub.core.file.store.FileInfo;
import com.ub.core.file.view.FileView;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileService {
    @Autowired
    private GridFsOperations gridFsTemplate;

    public GridFSDBFile getFile(ObjectId objectId) {
        List<GridFSDBFile> l = gridFsTemplate.find(new Query(Criteria.where("_id").is(objectId)).limit(1));
        if (l.size() > 0)
            return l.get(0);
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
        return gridFsTemplate.find(new Query(new Criteria()));
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
