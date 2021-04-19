package com.rcore.database.mongo.common.query;

import com.rcore.database.mongo.common.dto.SearchDTO;
import com.rcore.domain.base.entity.BaseEntity;
import com.rcore.domain.base.port.SearchRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Map;
import java.util.Optional;

public abstract class AbstractExampleQuery<Entity extends BaseEntity> implements ExampleQuery<Entity> {

    protected String query = "";
    protected Long limit = 20l;
    protected Long offset = 0l;

    protected Sort sort;
    protected String sortName = "id";
    protected Sort.Direction sortDirection = Sort.Direction.DESC;

    public AbstractExampleQuery() {
        this.sort = Sort.by(sortDirection, sortName);
    }

    public AbstractExampleQuery(SearchRequest request) {
        this.query = Optional.ofNullable(request.getQuery()).orElse("");
        this.limit = Optional.ofNullable(request.getLimit()).orElse(20l);
        this.offset = Optional.ofNullable(request.getOffset()).orElse(0l);
        this.sortName = Optional.ofNullable(request.getSortName()).orElse("_id");
        this.sortDirection = Optional.ofNullable(request.getSortDirection()).map(sort -> Sort.Direction.fromString(sort.name())).orElse(Sort.Direction.DESC);
        this.sort = Sort.by(sortDirection, sortName);
    }

    @Override
    public Query getQuery() {
        return Query.query(getCriteria())
                .limit(limit.intValue())
                .skip(offset)
                .with(sort);
    }
}
