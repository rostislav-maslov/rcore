package com.rcore.database.mongo.gridFs;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.data.mongodb.gridfs.GridFsResource;

import java.io.InputStream;

public interface GridFsRepository {

    GridFSFile save(InputStream content, String fileName, String contentType);

    GridFSFile findById(String id);

    GridFsResource getResource(GridFSFile fsFile);

    void delete(String id);

}
