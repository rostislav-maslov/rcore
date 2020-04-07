package com.rcore.database.mongo.common.query;

import com.rcore.domain.base.entity.BaseEntity;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public interface ExampleQuery<Entity extends BaseEntity> {

    Criteria getCriteria();

    default Query getQuery() {
        return Query.query(getCriteria());
    }

}
