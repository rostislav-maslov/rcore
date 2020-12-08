package com.rcore.database.mongo.commons.query;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public interface ExampleQuery {

    Criteria getCriteria();

    default Query getQuery() {
        return Query.query(getCriteria());
    }

}
