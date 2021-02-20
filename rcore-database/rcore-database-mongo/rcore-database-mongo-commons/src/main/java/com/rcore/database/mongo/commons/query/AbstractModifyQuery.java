package com.rcore.database.mongo.commons.query;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public abstract class AbstractModifyQuery implements ExampleQuery {
    protected Update update;
    protected FindAndModifyOptions modifyOptions;

    public abstract Update getUpdate();

    public abstract FindAndModifyOptions getModifyOptions();

    @Override
    public Query getQuery() {
        return new Query(getCriteria());
    }
}
