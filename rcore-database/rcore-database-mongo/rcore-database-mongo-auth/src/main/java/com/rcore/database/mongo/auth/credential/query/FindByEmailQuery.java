package com.rcore.database.mongo.auth.credential.query;

import com.rcore.database.mongo.commons.query.ExampleQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;

@RequiredArgsConstructor(staticName = "of")
public class FindByEmailQuery implements ExampleQuery {

    private final String email;
    
    @Override
    public Criteria getCriteria() {
        return Criteria.where("email").regex(email, "i");
    }
}
