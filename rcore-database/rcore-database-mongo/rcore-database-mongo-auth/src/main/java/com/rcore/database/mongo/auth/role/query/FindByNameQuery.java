package com.rcore.database.mongo.auth.role.query;

import com.rcore.database.mongo.commons.query.ExampleQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;

@RequiredArgsConstructor(staticName = "of")
public class FindByNameQuery implements ExampleQuery {

    private final String name;

    @Override
    public Criteria getCriteria() {
        return new Criteria("name").regex(name, "i");
    }
}
