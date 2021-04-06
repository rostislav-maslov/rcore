package com.rcore.database.mongo.picture.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import com.rcore.domain.picture.entity.PictureEntity;
import org.springframework.data.mongodb.core.query.Criteria;

public class DeleteUnused extends AbstractExampleQuery {

    @Override
    public Criteria getCriteria() {
        return new Criteria().andOperator(
                Criteria.where("isUsed").exists(true),
                Criteria.where("isUsed").is(false)
        );
    }
}