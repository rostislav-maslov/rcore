package com.rcore.database.mongo.commons.query;

import org.springframework.data.mongodb.core.query.Update;

public interface UpdateQuery {
    Update getUpdate();
}
