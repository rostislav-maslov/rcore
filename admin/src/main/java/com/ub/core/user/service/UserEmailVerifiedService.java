package com.ub.core.user.service;

import com.ub.core.user.models.UserDoc;
import com.ub.core.user.models.UserEmailVerifiedDoc;
import com.ub.core.user.service.exceptions.UserExistException;
import com.ub.core.user.service.exceptions.UserVerifiedErrorCodeException;
import com.ub.core.user.service.exceptions.UserVerifiedLimitException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class UserEmailVerifiedService {

    @Autowired private UserService userService;
    @Autowired private MongoTemplate mongoTemplate;

    public UserEmailVerifiedDoc findByEmail(String email) {
        return mongoTemplate.findOne(new Query(Criteria.where("email").is(email)), UserEmailVerifiedDoc.class);
    }

    public UserEmailVerifiedDoc findByEmailAndCode(String email, String code) {
        return mongoTemplate.findOne(new Query(Criteria.where("email").is(email).and("code").is(code)), UserEmailVerifiedDoc.class);
    }

    public UserEmailVerifiedDoc save(UserEmailVerifiedDoc userEmailVerifiedDoc) throws UserExistException {
        if (userEmailVerifiedDoc.getId() == null) {
            if (findByEmail(userEmailVerifiedDoc.getEmail()) != null) {
                throw new UserExistException();
            }
        }
        if (userEmailVerifiedDoc.getCode() == null || userEmailVerifiedDoc.getCode().equals("")) {
            userEmailVerifiedDoc.setCode(new ObjectId().toString());
        }
        mongoTemplate.save(userEmailVerifiedDoc);
        return userEmailVerifiedDoc;
    }

    public UserEmailVerifiedDoc create(UserEmailVerifiedDoc userEmailVerifiedDoc) throws UserExistException {
        UserDoc userDoc = userService.findByEmail(userEmailVerifiedDoc.getEmail());
        if (userDoc != null) throw new UserExistException();
        UserEmailVerifiedDoc oldDoc = findByEmail(userEmailVerifiedDoc.getEmail());
        if (oldDoc == null) {
            return save(userEmailVerifiedDoc);
        }
        if (oldDoc.getIsVerified()) {
            throw new UserExistException();
        }
        oldDoc.setPassword(userEmailVerifiedDoc.getPassword());
        save(oldDoc);
        return oldDoc;
    }

    public UserEmailVerifiedDoc verified(String email, String code) throws UserExistException, UserVerifiedLimitException, UserVerifiedErrorCodeException {
        UserEmailVerifiedDoc userEmailVerifiedDoc = findByEmail(email);
        if (userEmailVerifiedDoc == null) throw new UserExistException();

        if (userEmailVerifiedDoc.getIsVerified()) throw new UserExistException();

        if (userEmailVerifiedDoc.getNumberOfRetries() > 10) throw new UserVerifiedLimitException();

        userEmailVerifiedDoc.setNumberOfRetries(userEmailVerifiedDoc.getNumberOfRetries() + 1);
        userEmailVerifiedDoc = save(userEmailVerifiedDoc);

        if (userEmailVerifiedDoc.getCode().equals(code) == false) {
            throw new UserVerifiedErrorCodeException();
        }

        userEmailVerifiedDoc.setIsVerified(true);
        return save(userEmailVerifiedDoc);
    }

}
