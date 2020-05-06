package com.rcore.database.mongo.domain.file.query;

import com.rcore.database.mongo.common.query.AbstractExampleQuery;
import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.file.entity.FileEntity;
import org.springframework.data.mongodb.core.query.Criteria;

public class FindAllWithSearch extends AbstractExampleQuery<FileEntity> {

    public FindAllWithSearch(SearchRequest request) {
        super(request);
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
