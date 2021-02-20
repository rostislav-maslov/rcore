package com.rcore.database.mongo.commons.query;

import com.rcore.domain.commons.port.dto.SearchFilters;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Optional;

public abstract class AbstractExampleQuery implements ExampleQuery {

    protected String query = "";
    protected Long limit = 20l;
    protected Long offset = 0l;

    protected Sort sort;
    protected String sortName = "id";
    protected Sort.Direction sortDirection = Sort.Direction.DESC;

    public AbstractExampleQuery() {
        this.sort = Sort.by(sortDirection, sortName);
    }

    public AbstractExampleQuery(SearchFilters filters) {
        this.query = Optional.ofNullable(filters.getQuery()).orElse("");
        this.limit = Optional.ofNullable(filters.getLimit()).orElse(20l);
        this.offset = Optional.ofNullable(filters.getOffset()).orElse(0l);
        this.sortName = Optional.ofNullable(filters.getSortName()).orElse("_id");
        this.sortDirection = Optional.ofNullable(filters.getSortDirection()).map(Sort.Direction::fromString).orElse(Sort.Direction.DESC);
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
