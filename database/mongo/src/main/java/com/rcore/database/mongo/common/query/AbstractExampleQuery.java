package com.rcore.database.mongo.common.query;

import com.rcore.database.mongo.common.dto.SearchDTO;
import com.rcore.domain.base.entity.BaseEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

public abstract class AbstractExampleQuery<Entity extends BaseEntity> implements ExampleQuery<Entity> {

    protected String query = "";
    protected Integer limit = 20;
    protected Integer offset = 0;

    protected Sort sort;
    protected String sortName = "id";
    protected Sort.Direction sortDirection = Sort.Direction.DESC;

    public AbstractExampleQuery() {
        this.sort = Sort.by(sortDirection, sortName);
    }

    public AbstractExampleQuery(SearchDTO request) {
        this.query = request.getQuery();
        this.limit = request.getLimit();
        this.offset = request.getOffset();
        this.sortName = request.getSortName();
        this.sortDirection = request.getSortDirection();
        this.sort = Sort.by(sortDirection, sortName);
    }

    @Override
    public Query getQuery() {
        return Query.query(getCriteria())
                .limit(limit)
                .skip(offset)
                .with(sort);
    }
}
