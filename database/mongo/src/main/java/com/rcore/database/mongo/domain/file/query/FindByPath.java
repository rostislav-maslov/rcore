package com.rcore.database.mongo.domain.file.query;

import com.rcore.database.mongo.common.query.ExampleQuery;
import com.rcore.domain.file.entity.FileEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;

@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PUBLIC)
public class FindByPath implements ExampleQuery<FileEntity> {

    private final String path;

    @Override
    public Criteria getCriteria() {
        return Criteria.where("filePath").is(path);
    }
}
