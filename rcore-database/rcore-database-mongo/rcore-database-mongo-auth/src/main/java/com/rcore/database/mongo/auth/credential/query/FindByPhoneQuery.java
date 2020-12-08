package com.rcore.database.mongo.auth.credential.query;

import com.rcore.database.mongo.commons.query.ExampleQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;

@RequiredArgsConstructor(staticName = "of")
public class FindByPhoneQuery implements ExampleQuery {

    private final String phone;

    @Override
    public Criteria getCriteria() {
        return Criteria.where("phone").regex(phone, "i");
    }
}
