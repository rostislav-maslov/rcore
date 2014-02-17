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

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Component
public class UserService {
    @Autowired
    protected IEmailUserDocService emailUserDocService;

    @Autowired
    protected IUserDocService userDocService;

    @Autowired
    protected IRoleDocService roleDocService;

    public void saveEmailUser(AddEditUserView addEditUserView) throws UserServiceException {
        EmailUserDoc emailUserDoc = new EmailUserDoc();
        UserDoc userDoc = new UserDoc();

        userDoc.setStatus(Boolean.TRUE);
        ArrayList<RoleDoc> roleDocArrayList = new ArrayList<RoleDoc>();
        RoleDoc roleDoc = null;
        if (addEditUserView.getRole() != null) {
            roleDoc = roleDocService.findOne(new ObjectId(addEditUserView.getRole()));
            if (roleDoc == null) {
                throw new UserServiceException("Данной роли не существует");
            }
            roleDocArrayList.add(roleDoc);
        }

        userDoc.setRoleDocList(roleDocArrayList);

        emailUserDoc.setEmail(addEditUserView.getEmail());
        emailUserDoc.setPassword(DigestUtils.md5Hex(addEditUserView.getPassword()));
        userDocService.save(userDoc);

        emailUserDoc.setUserDoc(userDoc);

        emailUserDocService.save(emailUserDoc);
    }

    public ArrayList<RoleDoc> getAllRoles() {
        ArrayList<RoleDoc> roleDocs = new ArrayList<RoleDoc>();

        Iterable<RoleDoc> roleDocIterable = roleDocService.findAll();
        for(RoleDoc roleDoc : roleDocIterable){
            Boolean hasElement = false;
            for(RoleDoc roleDocList : roleDocs){
                if(roleDoc.getRoleTitle().equals(roleDocList.getRoleTitle())){
                    hasElement = true;
                    break;
                }
            }
            if(hasElement == false){
                roleDocs.add(roleDoc);
            }
        }

        return roleDocs;
    }

    public ArrayList<EmailUserDoc> getEmailUsers() {
        return Lists.newArrayList(emailUserDocService.findAll());
    }

    public void deleteUser(String id) {

//        List<EmailUserDoc> userDocList = emailUserDocService.findByEmail(email);

        emailUserDocService.delete(id);
    }

    public AddEditUserView getUser(String id) {
        AddEditUserView addEditUserView = new AddEditUserView();
        EmailUserDoc emailUserDoc = emailUserDocService.findOne(id);

        addEditUserView.setEmail(emailUserDoc.getEmail());

//       TODO: поменять на множественный список
        return addEditUserView;


    }

    public void updateUser(String id, AddEditUserView addEditUserView) throws UserServiceException {

        EmailUserDoc emailUserDoc = emailUserDocService.findOne(id);
        UserDoc userDoc = userDocService.findOne(emailUserDoc.getUserDoc().getId());

        userDoc.setStatus(Boolean.TRUE);
        ArrayList<RoleDoc> roleDocArrayList = new ArrayList<RoleDoc>();
        RoleDoc roleDoc = roleDocService.findOne(new ObjectId(addEditUserView.getRole()));
        if (roleDoc == null) {
            throw new UserServiceException("Данной роли не существует");
        }
        roleDocArrayList.add(roleDoc);

        userDoc.setRoleDocList(roleDocArrayList);

        //emailUserDoc.setEmail(addEditUserView.getEmail());
        if (addEditUserView.getPassword() != null && addEditUserView.getPassword().length() > 6) {
            emailUserDoc.setPassword(DigestUtils.md5Hex(addEditUserView.getPassword()));
        }
        userDocService.save(userDoc);

        emailUserDoc.setUserDoc(userDoc);

        emailUserDocService.save(emailUserDoc);


    }

    public void saveRole(AddEditRoleView addEditRoleView) {
        RoleDoc roleDoc = new RoleDoc();
        roleDoc.setRoleTitle(addEditRoleView.getRoleTitle());
        roleDoc.setRoleDescription(addEditRoleView.getRoleDescription());

        roleDocService.save(roleDoc);
    }

    public EmailUserDoc getUserByEmail(String email) {
        if (emailUserDocService.findById(email).size() != 0) {
            return emailUserDocService.findById(email).get(0);
        } else {
            return null;
        }

    }

    public EmailUserDoc getAuthenticatedUser(HttpSession session) {
        if (session.getAttribute("userEmail") != null) {
            return emailUserDocService.findById(session.getAttribute("userEmail").toString()).get(0);

        } else {
            return null;
        }
    }

    protected String generateVerificationCode(String email) {
        return DigestUtils.md5Hex(email + "42");

    }

    public IEmailUserDocService getEmailUserDocService() {
        return emailUserDocService;
    }

    public void setEmailUserDocService(IEmailUserDocService emailUserDocService) {
        this.emailUserDocService = emailUserDocService;
    }

    public IUserDocService getUserDocService() {
        return userDocService;
    }

    public void setUserDocService(IUserDocService userDocService) {
        this.userDocService = userDocService;
    }

    public IRoleDocService getRoleDocService() {
        return roleDocService;
    }

    public void setRoleDocService(IRoleDocService roleDocService) {
        this.roleDocService = roleDocService;
    }

    public void updateEmailUser(EmailUserDoc emailUserDoc) {
        emailUserDocService.save(emailUserDoc);
    }
}
