package com.ub.core.user.service;

import com.google.common.collect.Lists;
import com.ub.core.user.models.EmailUserDoc;
import com.ub.core.user.models.RoleDoc;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.views.AddUserView;
import org.apache.commons.codec.digest.DigestUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class EmailUserDocService {
    @Autowired
    private IEmailUserDocService emailUserDocService;

    @Autowired
    private IUserDocService userDocService;

    @Autowired
    private IRoleDocService roleDocService;

    public void saveEmailUser(AddUserView addUserView){
        EmailUserDoc emailUserDoc = new EmailUserDoc();
        UserDoc userDoc = new UserDoc();

        userDoc.setStatus(Boolean.TRUE);
        ArrayList<RoleDoc> roleDocArrayList = new ArrayList<RoleDoc>();
        RoleDoc roleDoc = roleDocService.findOne(new ObjectId(addUserView.getRole()));
        if(roleDoc == null){
            //TODO: заменить на свой класс и вывести ошибку о том что такой роли нет
            throw  new RuntimeException();
        }
        roleDocArrayList.add( roleDoc );

        userDoc.setRoleDocList( roleDocArrayList );

        emailUserDoc.setEmail(addUserView.getEmail());
        emailUserDoc.setPassword(DigestUtils.md5Hex(addUserView.getPassword()));
        userDocService.save(userDoc);

        emailUserDoc.setUserDoc(userDoc);

        emailUserDocService.save(emailUserDoc);
    }

    public ArrayList<RoleDoc> getAllRoles(){
        return Lists.newArrayList(roleDocService.findAll());
    }

    public ArrayList<EmailUserDoc> getEmailUsers(){
        return Lists.newArrayList(emailUserDocService.findAll());
    }



}
