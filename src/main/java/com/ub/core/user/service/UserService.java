package com.ub.core.user.service;

import com.google.common.collect.Lists;
import com.ub.core.user.models.EmailUserDoc;
import com.ub.core.user.models.RoleDoc;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.service.exceptions.UserServiceException;
import com.ub.core.user.views.AddEditRoleView;
import com.ub.core.user.views.AddEditUserView;
import org.apache.commons.codec.digest.DigestUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserService {
    @Autowired
    protected IEmailUserDocService emailUserDocService;

    @Autowired
    private IUserDocService userDocService;

    @Autowired
    private IRoleDocService roleDocService;

    public void saveEmailUser(AddEditUserView addEditUserView) throws UserServiceException {
        EmailUserDoc emailUserDoc = new EmailUserDoc();
        UserDoc userDoc = new UserDoc();

        userDoc.setStatus(Boolean.TRUE);
        ArrayList<RoleDoc> roleDocArrayList = new ArrayList<RoleDoc>();
        RoleDoc roleDoc = null;
        if(addEditUserView.getRole()!=null){
            roleDoc = roleDocService.findOne(new ObjectId(addEditUserView.getRole()));
            if(roleDoc == null){
                throw  new UserServiceException("Данной роли не существует");
            }
            roleDocArrayList.add( roleDoc );
        }

        userDoc.setRoleDocList( roleDocArrayList );

        emailUserDoc.setEmail(addEditUserView.getEmail());
        emailUserDoc.setPassword(DigestUtils.md5Hex(addEditUserView.getPassword()));
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

    public void deleteUser(String id){

//        List<EmailUserDoc> userDocList = emailUserDocService.findByEmail(email);

        emailUserDocService.delete(id);
    }
    public AddEditUserView getUser(String id){
        AddEditUserView addEditUserView = new AddEditUserView();
        EmailUserDoc emailUserDoc = emailUserDocService.findOne(id);

        addEditUserView.setEmail(emailUserDoc.getEmail());

//       TODO: поменять на множественный список
        return addEditUserView;


    }

    public void updateUser(String id, AddEditUserView addEditUserView) throws UserServiceException {

        EmailUserDoc emailUserDoc = emailUserDocService.findOne(id);
        UserDoc userDoc = new UserDoc();

        userDoc.setStatus(Boolean.TRUE);
        ArrayList<RoleDoc> roleDocArrayList = new ArrayList<RoleDoc>();
        RoleDoc roleDoc = roleDocService.findOne(new ObjectId(addEditUserView.getRole()));
        if(roleDoc == null){
            throw  new UserServiceException("Данной роли не существует");
        }
        roleDocArrayList.add( roleDoc );

        userDoc.setRoleDocList( roleDocArrayList );

        emailUserDoc.setEmail(addEditUserView.getEmail());
        emailUserDoc.setPassword(DigestUtils.md5Hex(addEditUserView.getPassword()));
        userDocService.save(userDoc);

        emailUserDoc.setUserDoc(userDoc);

        emailUserDocService.save(emailUserDoc);


    }
    public void saveRole(AddEditRoleView addEditRoleView){
        RoleDoc roleDoc = new RoleDoc();
        roleDoc.setRoleTitle(addEditRoleView.getRoleTitle());
        roleDoc.setRoleDescription(addEditRoleView.getRoleDescription());

        roleDocService.save(roleDoc);
    }
    public EmailUserDoc getUserByEmail(String email){
        return emailUserDocService.findByEmail(email).get(0);
    }
    protected String generateVerificationCode(String email){
        return DigestUtils.md5Hex(email + "42");

    }


}
