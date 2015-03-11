package com.ub.core.user.service;

import com.ub.core.user.models.UserDoc;
import com.ub.core.user.models.UserEmailPasswordRecoverDoc;
import com.ub.core.user.service.exceptions.UserNotExistException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class UserEmailPasswordRecoveryService {

    @Autowired private MongoTemplate mongoTemplate;
    @Autowired private UserService userService;

    public UserEmailPasswordRecoverDoc findByCodeAndId(ObjectId id, String code){
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id).and("code").is(code)),
                UserEmailPasswordRecoverDoc.class);
    }

    public UserEmailPasswordRecoverDoc save(UserEmailPasswordRecoverDoc userEmailPasswordRecoverDoc){
        mongoTemplate.save(userEmailPasswordRecoverDoc);
        return   userEmailPasswordRecoverDoc;
    }

    public UserEmailPasswordRecoverDoc createRecovery(String email)throws UserNotExistException{
        UserDoc userDoc = userService.findByEmail(email);
        if(userDoc == null) throw new UserNotExistException();

        UserEmailPasswordRecoverDoc userEmailPasswordRecoverDoc = new UserEmailPasswordRecoverDoc();
        userEmailPasswordRecoverDoc.setCode(new ObjectId().toString());
        userEmailPasswordRecoverDoc.setUserId(userDoc.getId());
        return save(userEmailPasswordRecoverDoc);
    }

}
