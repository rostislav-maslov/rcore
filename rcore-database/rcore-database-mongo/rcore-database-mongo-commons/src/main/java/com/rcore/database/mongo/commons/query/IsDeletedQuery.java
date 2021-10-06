package com.rcore.database.mongo.commons.query;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class IsDeletedQuery extends Query {

    public IsDeletedQuery(boolean isDeleted) {
        super(Criteria.where("isDeleted").is(isDeleted));
    }

    public static IsDeletedQuery deleted() {
        return new IsDeletedQuery(true);
    }

    public static IsDeletedQuery notDeleted() {
        return new IsDeletedQuery(false);
    }
}
