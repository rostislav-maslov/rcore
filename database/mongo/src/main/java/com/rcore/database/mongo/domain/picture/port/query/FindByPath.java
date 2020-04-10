package com.rcore.database.mongo.domain.picture.port.query;

import com.rcore.database.mongo.common.query.ExampleQuery;
import com.rcore.domain.picture.entity.PictureEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;

@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PUBLIC)
public class FindByPath implements ExampleQuery<PictureEntity> {

    private final String path;

    @Override
    public Criteria getCriteria() {
        return Criteria.where("filePath").is(path);
    }
}
