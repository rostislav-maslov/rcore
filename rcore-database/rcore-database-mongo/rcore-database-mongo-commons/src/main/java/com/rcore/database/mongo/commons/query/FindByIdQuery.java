package com.rcore.database.mongo.commons.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;

@RequiredArgsConstructor(staticName = "of")
public class FindByIdQuery<DocId> implements ExampleQuery {
    private final DocId id;

    @Override
    public Criteria getCriteria() {
        return Criteria.where("_id").is(id);
    }
}
