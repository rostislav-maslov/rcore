package com.ub.core.base.starter;

import com.ub.core.base.role.BaseAdminRole;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.service.IUserDocService;
import com.ub.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitMainUsersStarter extends ACoreStarter {

    @Autowired private UserService userService;
    @Autowired private IUserDocService iUserDocService;

    @Override
    protected void onStart() {
        create("maslov@unitbean.com", "4rfVBgt5","Ростислав","Маслов");
        create("nabiullin@unitbean.com","4rfVBgt5","Антон","Набиуллин");
    }

    private void create(String email, String password, String name, String lastName){
        UserDoc userDoc = userService.getUserByEmail(email);
        if (userDoc != null) return;

        userDoc = new UserDoc();
        userDoc.setEmail(email);
        userDoc.setPasswordAsHex(password);
        userDoc.getRoles().add(new BaseAdminRole());
        userDoc.setFirstName(name);
        userDoc.setLastName(lastName);

        iUserDocService.save(userDoc);
    }
}
