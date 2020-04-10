package com.rcore.database.mongo.gridFs;

import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import java.io.InputStream;

@RequiredArgsConstructor
@Repository
public class GridFsRepositoryImpl implements GridFsRepository {

    private final GridFsTemplate gridFsTemplate;

    @Override
    public GridFSFile save(InputStream content, String fileName, String contentType) {
        return findById(gridFsTemplate.store(content, fileName, contentType).toString());
    }

    @Override
    public GridFSFile findById(String id) {
        return gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
    }

    @Override
    public GridFsResource getResource(GridFSFile fsFile) {
        return gridFsTemplate.getResource(fsFile);
    }

    @Override
    public void delete(String id) {
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
    }
}
