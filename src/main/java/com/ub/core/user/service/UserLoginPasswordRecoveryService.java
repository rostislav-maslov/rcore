package com.ub.core.user.service;

import com.ub.core.user.models.UserDoc;
import com.ub.core.user.models.UserLoginPasswordRecoveryDoc;
import com.ub.core.user.models.UserStatusEnum;
import com.ub.core.user.service.exceptions.UserNotExistException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class UserLoginPasswordRecoveryService {

    @Autowired private MongoTemplate mongoTemplate;
    @Autowired private UserService userService;

    public UserLoginPasswordRecoveryDoc findByCodeAndId(ObjectId id, String code) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id).and("code").is(code)),
                UserLoginPasswordRecoveryDoc.class);
    }

    public UserLoginPasswordRecoveryDoc save(UserLoginPasswordRecoveryDoc userLoginPasswordRecoveryDoc) {
        mongoTemplate.save(userLoginPasswordRecoveryDoc);
        return userLoginPasswordRecoveryDoc;
    }

    public UserLoginPasswordRecoveryDoc createRecovery(String emailForLogin) throws UserNotExistException {
        UserDoc userDoc = userService.findByEmailForLogin(emailForLogin);
        if (userDoc == null) throw new UserNotExistException();

        UserLoginPasswordRecoveryDoc userLoginPasswordRecoveryDoc = new UserLoginPasswordRecoveryDoc();
        userLoginPasswordRecoveryDoc.setCode(new ObjectId().toString());
        userLoginPasswordRecoveryDoc.setUserId(userDoc.getId());


        return save(userLoginPasswordRecoveryDoc);
    }

    public UserLoginPasswordRecoveryDoc findByEmail(String emailForLogin) {
        UserDoc userDoc = userService.findByEmailForLogin(emailForLogin);
        if (userDoc == null) {
            return null;
        }
        Criteria criteria = Criteria.where("userId").is(userDoc.getId()).and("isRecovered").is(false);
        Query query = new Query(criteria);
        UserLoginPasswordRecoveryDoc userLoginPasswordRecoveryDoc = mongoTemplate.findOne(query, UserLoginPasswordRecoveryDoc.class);
        return userLoginPasswordRecoveryDoc;
    }

    public void recoveryPassword(ObjectId id, String code, String password) throws Exception {
        UserLoginPasswordRecoveryDoc userLoginPasswordRecoveryDoc = findByCodeAndId(id, code);
        if (userLoginPasswordRecoveryDoc.getIsRecovered()) throw new Exception();
        UserDoc userDoc = userService.getUser(userLoginPasswordRecoveryDoc.getUserId());
        userDoc.setPasswordForLoginAsHex(password);
        userDoc.setUserStatus(UserStatusEnum.ACTIVE);
        userDoc.setFails(0);

        userService.save(userDoc);
        userLoginPasswordRecoveryDoc.setIsRecovered(true);
        save(userLoginPasswordRecoveryDoc);

    }
}
