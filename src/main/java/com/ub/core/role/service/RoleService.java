package com.ub.core.role.service;

import com.ub.core.base.role.Role;
import com.ub.core.role.models.RoleDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleService {
    @Autowired private MongoTemplate mongoTemplate;

    public RoleDoc findById(String id){
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), RoleDoc.class);
    }

    public RoleDoc save(Role role){
        RoleDoc roleDoc = new RoleDoc(role);
        mongoTemplate.save(roleDoc);
        return roleDoc;
    }

    public List<RoleDoc> findAllRoles() {
        return mongoTemplate.findAll(RoleDoc.class);
    }
}
