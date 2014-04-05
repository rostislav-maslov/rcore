package com.ub.core.user.service;

import com.google.common.collect.Lists;
import com.ub.core.base.role.Role;
import com.ub.core.base.role.RoleBoost;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.models.UserStatusEnum;
import com.ub.core.user.service.exceptions.UserExistException;
import com.ub.core.user.service.exceptions.UserNotExistException;
import com.ub.core.user.views.AddEditUserView;
import org.apache.commons.codec.digest.DigestUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserService {

    @Autowired
    protected IUserDocService userDocService;

    /**
     * Блокировка пользотеля
     *
     * @param id
     */
    public void block(ObjectId id) {
        UserDoc userDoc = userDocService.findOne(id);
        userDoc.setUserStatus(UserStatusEnum.BLOCK);
        userDocService.save(userDoc);
    }

    /**
     * Активация пользователя
     *
     * @param id
     */
    public void active(ObjectId id) {
        UserDoc userDoc = userDocService.findOne(id);
        userDoc.setUserStatus(UserStatusEnum.ACTIVE);
        userDocService.save(userDoc);
    }

    /**
     * Создание нового пользователя по email
     *
     * @param addEditUserView
     * @throws UserExistException
     */
    public void createUserByEmail(AddEditUserView addEditUserView) throws UserExistException {
        UserDoc userDoc = new UserDoc();

        UserDoc check = userDocService.findByEmail(addEditUserView.getEmail());
        if (check != null) {
            throw new UserExistException();
        }

        if (addEditUserView.getRole() != null) {
            List<Role> roles = getAllRoles();
            Role role = null;
            for (Role r : roles) {
                if (r.getId().equals(addEditUserView.getRole())) {
                    role = r;
                    break;
                }
            }
            if (role != null) {
                userDoc.getRoles().add(role);
            }
        }

        userDoc.setEmail(addEditUserView.getEmail());
        userDoc.setPasswordAsHex(addEditUserView.getPassword());
        userDocService.save(userDoc);
    }


    /**
     * Получить все доступные роли в системе
     *
     * @return
     */
    public List<Role> getAllRoles() {
        return RoleBoost.allRoles();
    }

    /**
     * Получить всех пользователей
     *
     * @return
     */
    public ArrayList<UserDoc> getAllUsers() {
        return Lists.newArrayList(userDocService.findAll());
    }

    /**
     * Удалить пользователя
     *
     * @param id
     */
    public void deleteUser(ObjectId id) {
        userDocService.delete(id);
    }

    /**
     * @param id
     * @return
     */
    public UserDoc getUser(ObjectId id) {
        return userDocService.findOne(id);
    }

    /**
     * Обновляем информацию о пользователе
     *
     * @param userDoc
     * @throws UserNotExistException
     */
    public void updateUserInfo(UserDoc userDoc) throws UserNotExistException {
        UserDoc currUser = userDocService.findOne(userDoc.getId());
        if (currUser == null) {
            throw new UserNotExistException();
        }

        currUser.setFirstName(userDoc.getFirstName());
        currUser.setLastName(userDoc.getLastName());

        userDocService.save(userDoc);
    }

    /**
     * Получить пользователя по email
     *
     * @param email
     * @return
     */
    public UserDoc getUserByEmail(String email) {
        return userDocService.findByEmail(email);
    }

    public UserDoc getUserByVkId(String vkId){
        return userDocService.findByUserVkId(vkId);
    }

    public String restorePassword(String email) throws UserNotExistException{
        String pass = DigestUtils.md2Hex(new Date().toString());
        UserDoc userDoc = getUserByEmail(email);
        if(userDoc == null)
            throw new UserNotExistException();
        userDoc.setPasswordAsHex(pass);
        userDocService.save(userDoc);
        return pass;
    }

    public IUserDocService getUserDocService() {
        return userDocService;
    }

    public void setUserDocService(IUserDocService userDocService) {
        this.userDocService = userDocService;
    }
}
