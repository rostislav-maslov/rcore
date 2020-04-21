package com.rcore.database.mongo.user.usecase;

import com.rcore.database.mongo.domain.user.model.UserDoc;
import com.rcore.domain.user.port.PasswordGenerator;

public class AuthenticationTestData {

    public static UserDoc user(String email, String pass, PasswordGenerator passwordGenerator){
        UserDoc user = new UserDoc();
        user.setId(email);
        user.setEmail(email);
        user.setPassword(passwordGenerator.generate(email, pass));
        return user;
    }

    public static UserDoc user1(PasswordGenerator passwordGenerator) {
        return user("user1", "123", passwordGenerator);
    }

    public static UserDoc user2(PasswordGenerator passwordGenerator) {
        return user("user2", "123", passwordGenerator);
    }

}
