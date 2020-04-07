package com.rcore.database.mongo.common.query;

import com.rcore.domain.base.entity.BaseEntity;
import lombok.Getter;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public abstract class AbstractModifyQuery<Entity extends BaseEntity> implements ExampleQuery<Entity> {
    protected Update update;
    protected FindAndModifyOptions modifyOptions;

    public abstract Update getUpdate();

    public abstract FindAndModifyOptions getModifyOptions();

    @Override
    public Query getQuery() {
        return new Query(getCriteria());
    }
}
