package com.ub.core.user.service;

import com.google.common.collect.Lists;
import com.ub.core.base.role.Role;
import com.ub.core.role.models.RoleDoc;
import com.ub.core.role.service.RoleService;
import com.ub.core.security.service.AutorizationService;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.models.UserEmailPasswordRecoverDoc;
import com.ub.core.user.models.UserEmailVerifiedDoc;
import com.ub.core.user.models.UserStatusEnum;
import com.ub.core.user.service.exceptions.UserExistException;
import com.ub.core.user.service.exceptions.UserNotExistException;
import com.ub.core.user.service.exceptions.UserVerifiedErrorCodeException;
import com.ub.core.user.service.exceptions.UserVerifiedLimitException;
import com.ub.core.user.views.AddEditUserView;
import com.ub.core.user.views.modalUserSearch.all.SearchUserAdminRequest;
import com.ub.core.user.views.modalUserSearch.all.SearchUserAdminResponse;
import com.ub.facebook.response.FBUserInfo;
import com.ub.vk.response.AccessTokenResponse;
import com.ub.vk.response.users.get.UserInfo;
import com.ub.vk.response.users.get.UsersGetResponse;
import com.ub.vk.services.UserVkService;
import org.apache.commons.codec.digest.DigestUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserService {

    @Autowired protected MongoTemplate mongoTemplate;
    @Autowired protected UserVkService userVkService;
    @Autowired private RoleService roleService;
    @Autowired private UserEmailVerifiedService userEmailVerifiedService;
    @Autowired private UserEmailPasswordRecoveryService userEmailPasswordRecoveryService;
    @Autowired private AutorizationService autorizationService;

    /**
     * Блокировка пользотеля
     *
     * @param id
     */
    public void block(ObjectId id) {
        UserDoc userDoc = mongoTemplate.findById(id, UserDoc.class);
        userDoc.setUserStatus(UserStatusEnum.BLOCK);
        try {
            save(userDoc);
        }catch (UserExistException e){
            e.printStackTrace();
        }
    }

    /**
     * Активация пользователя
     *
     * @param id
     */
    public void active(ObjectId id) {
        UserDoc userDoc = mongoTemplate.findById(id, UserDoc.class);
        userDoc.setUserStatus(UserStatusEnum.ACTIVE);
        try {
            save(userDoc);
        } catch (UserExistException e) {
            e.printStackTrace();
        }
    }

    public UserDoc findByEmail(String email) {
        return mongoTemplate.findOne(new Query(Criteria.where("email").is(email)), UserDoc.class);
    }

    public UserDoc save(UserDoc userDoc) throws UserExistException {
        if (userDoc.getId() == null && userDoc.getEmail() != null) {
            UserDoc old = findByEmail(userDoc.getEmail());
            if (old != null) {
                throw new UserExistException();
            }
        }

        if (userDoc.getId() != null && userDoc.getEmail() != null) {
            UserDoc oldDoc = getUser(userDoc.getId());
            if (oldDoc == null) {
                UserDoc old = findByEmail(userDoc.getEmail());
                if (old != null) {
                    throw new UserExistException();
                }
            }
        }

        mongoTemplate.save(userDoc);
        return userDoc;
    }

    public void deleteRoleToUser(ObjectId idUser, Role role) {
        UserDoc userDoc = mongoTemplate.findById(idUser, UserDoc.class);
        if (userDoc != null) {
            List<Role> toDelete = new ArrayList<Role>();
            for (Role r : userDoc.getRoles()) {
                if (r.getId().equals(role.getId())) toDelete.add(r);
            }
            userDoc.getRoles().removeAll(toDelete);
            try {
                save(userDoc);
            }catch (UserExistException e){
                e.printStackTrace();
            }
        }
    }

    public void addRoleToUser(ObjectId idUser, Role role) {
        UserDoc userDoc = mongoTemplate.findById(idUser, UserDoc.class);
        if (userDoc != null) {
            userDoc.getRoles().add(role);
            try {
                save(userDoc);
            }catch (UserExistException e){
                e.printStackTrace();
            }
        }
    }

    public void addRoleToUser(ObjectId idUser, RoleDoc roleDoc) {
        Role role = new Role(roleDoc);
        addRoleToUser(idUser, role);
    }

    public void deleteRoleToUser(ObjectId idUser, RoleDoc roleDoc) {
        Role role = new Role(roleDoc);
        deleteRoleToUser(idUser, role);
    }

    /**
     * Создание нового пользователя по email
     *
     * @param addEditUserView
     * @throws UserExistException
     */
    @Deprecated
    public void createUserByEmail(AddEditUserView addEditUserView) throws UserExistException {
        createUserByEmail(addEditUserView.getEmail(), addEditUserView.getPassword(), addEditUserView.getRole());
    }

    public UserDoc userVerifiedEmail(String email, String code) throws UserExistException, UserVerifiedErrorCodeException, UserVerifiedLimitException {
        UserEmailVerifiedDoc userEmailVerifiedDoc = userEmailVerifiedService.verified(email, code);
        UserDoc userDoc = new UserDoc();
        userDoc.setRoles(userEmailVerifiedDoc.getRoles());
        userDoc.setEmail(userEmailVerifiedDoc.getEmail());
        userDoc.setPassword(userEmailVerifiedDoc.getPassword());
        userDoc.setUserStatus(userEmailVerifiedDoc.getUserStatus());
        userDoc.setLastName(userEmailVerifiedDoc.getLastName());
        userDoc.setFirstName(userEmailVerifiedDoc.getFirstName());


        userDoc = save(userDoc);
        autorizationService.authorizeUserDoc(userDoc);
        return userDoc;
    }

    /**
     * @param email
     * @param password
     * @return
     * @throws UserExistException
     */
    public UserDoc createUserByEmail(String email, String password) throws UserExistException {
        UserDoc userDoc = new UserDoc();
        UserDoc check = findByEmail(email);
        if (check != null) {
            throw new UserExistException();
        }
        userDoc.setEmail(email);
        userDoc.setPasswordAsHex(password);
        save(userDoc);
        return userDoc;
    }

    /**
     * @param email
     * @param password
     * @param roleString
     * @return
     * @throws UserExistException
     */
    public UserDoc createUserByEmail(String email, String password, String roleString) throws UserExistException {
        UserDoc userDoc = createUserByEmail(email, password);
        if (roleString != null) {
            List<RoleDoc> roles = roleService.findAllRoles();
            Role role = null;
            for (RoleDoc roleDoc : roles) {
                if (roleDoc.getId().equals(roleString)) {
                    role = new Role(roleDoc);
                    break;
                }
            }
            if (role != null) {
                userDoc.getRoles().add(role);
            }
        }
        return save(userDoc);
    }

    /**
     * @param email
     * @param password
     * @return
     * @throws UserExistException
     */
    public UserEmailVerifiedDoc createUserByEmailWithVerified(String email, String password) throws UserExistException {
        return createUserByEmailWithVerified(email, password, "", "");
    }

    /**
     * @param email
     * @param password
     * @param lastName
     * @param firstName
     * @return
     * @throws UserExistException
     */
    public UserEmailVerifiedDoc createUserByEmailWithVerified(String email, String password, String lastName,
                                                              String firstName) throws UserExistException {
        UserEmailVerifiedDoc userEmailVerifiedDoc = new UserEmailVerifiedDoc();
        userEmailVerifiedDoc.setEmail(email);
        userEmailVerifiedDoc.setPasswordAsHex(password);
        userEmailVerifiedDoc.setLastName(lastName);
        userEmailVerifiedDoc.setFirstName(firstName);
        return userEmailVerifiedService.create(userEmailVerifiedDoc);
    }

    /**
     * Создание нового пользователя по vk
     *
     * @param accessTokenResponse
     * @throws UserExistException
     */
    public UserDoc createUserByVk(AccessTokenResponse accessTokenResponse) throws UserExistException {
        UserDoc userDoc = new UserDoc();

        UserDoc check = getUserByVkId(accessTokenResponse.getUser_id());
        if (check != null) {
            throw new UserExistException();
        }

        userDoc.setVkAccessToken(accessTokenResponse.getAccess_token());
        userDoc.setVkEmail(accessTokenResponse.getEmail());
        userDoc.setVkId(accessTokenResponse.getUser_id());
        userDoc.setUserStatus(UserStatusEnum.ACTIVE);

        UsersGetResponse usersGetResponse = userVkService.get(accessTokenResponse.getUser_id());
        if (usersGetResponse != null && usersGetResponse.getResponse() != null && usersGetResponse.getResponse().size() == 1) {
            UserInfo userInfo = usersGetResponse.getResponse().get(0);

            userDoc.setFirstName(userInfo.getFirst_name());
            userDoc.setLastName(userInfo.getLast_name());
        }

        save(userDoc);
        return userDoc;
    }

    public UserDoc createUserByFb(FBUserInfo userInfo) throws UserExistException {
        UserDoc userDoc = new UserDoc();

        UserDoc check = getUserByFbId(userInfo.getId());
        if (check != null) {
            throw new UserExistException();
        }

        userDoc.setFbAccessToken(userInfo.getAccessToken());
        userDoc.setEmail(userInfo.getEmail());
        userDoc.setFbId(userInfo.getId());
        userDoc.setUserStatus(UserStatusEnum.ACTIVE);

        userDoc.setFirstName(userInfo.getFirst_name());
        userDoc.setLastName(userInfo.getLast_name());

        save(userDoc);
        return userDoc;
    }

    public UserDoc updateVkAccessToken(UserDoc userDoc, String accessToken) {
        userDoc.setVkAccessToken(accessToken);
        try {
            save(userDoc);
        }catch (UserExistException e){
            e.printStackTrace();
        }
        return userDoc;
    }

    public UserDoc updateFbAccessToken(UserDoc userDoc, String accessToken) {
        userDoc.setFbAccessToken(accessToken);
        try {
            save(userDoc);
        } catch (UserExistException e) {
            e.printStackTrace();
        }
        return userDoc;
    }

    /**
     * Получить всех пользователей
     *
     * @return
     */
    public ArrayList<UserDoc> getAllUsers() {
        return Lists.newArrayList(mongoTemplate.findAll(UserDoc.class));
    }

    /**
     * Удалить пользователя
     *
     * @param id
     */
    public void deleteUser(ObjectId id) {
        UserDoc userDoc = mongoTemplate.findById(id, UserDoc.class);
        mongoTemplate.remove(userDoc);
    }

    /**
     * @param id
     * @return
     */
    public UserDoc getUser(ObjectId id) {
        return mongoTemplate.findById(id, UserDoc.class);
    }

    /**
     * Обновляем информацию о пользователе
     *
     * @param userDoc
     * @throws UserNotExistException
     */
    public void updateUserInfo(UserDoc userDoc) throws UserNotExistException {
        UserDoc currUser = mongoTemplate.findById(userDoc.getId(), UserDoc.class);
        if (currUser == null) {
            throw new UserNotExistException();
        }

        currUser.setFirstName(userDoc.getFirstName());
        currUser.setLastName(userDoc.getLastName());

        try {
            save(currUser);
        }catch (UserExistException e){
            e.printStackTrace();
        }
    }

    /**
     * Получить пользователя по email
     *
     * @param email
     * @return
     */
    public UserDoc getUserByEmail(String email) {
        return findByEmail(email);
    }

    public UserDoc getUserByVkId(String vkId) {
        return mongoTemplate.findOne(new Query(Criteria.where("vkId").is(vkId)), UserDoc.class);
    }

    public UserDoc getUserByFbId(String fbId) {
        return mongoTemplate.findOne(new Query(new Criteria("fbId").is(fbId)), UserDoc.class);
    }

    public String restorePassword(String email) throws UserNotExistException, UserExistException {
        String pass = DigestUtils.md2Hex(new Date().toString());
        UserDoc userDoc = getUserByEmail(email);
        if (userDoc == null)
            throw new UserNotExistException();
        userDoc.setPasswordAsHex(pass);
        save(userDoc);
        return pass;
    }

    public void changePassword(ObjectId id, String password) {
        UserDoc userDoc = getUser(id);
        userDoc.setPasswordAsHex(password);
        try {
            save(userDoc);
        }catch (UserExistException e){
            e.printStackTrace();
        }
    }

    /**
     * @param searchUserAdminRequest
     * @return
     */
    public SearchUserAdminResponse findAll(SearchUserAdminRequest searchUserAdminRequest) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(
                searchUserAdminRequest.getCurrentPage(),
                searchUserAdminRequest.getPageSize(),
                sort);

        Criteria criteria = new Criteria();
        criteria = criteria.orOperator(
                Criteria.where("lastName").regex(searchUserAdminRequest.getQuery(), "i"),
                Criteria.where("firstName").regex(searchUserAdminRequest.getQuery(), "i")
        );


        Query query = new Query(criteria);
        Long count = mongoTemplate.count(query, UserDoc.class);
        query = query.with(pageable);

        List<UserDoc> result = mongoTemplate.find(query, UserDoc.class);
        SearchUserAdminResponse searchUserAdminResponse = new SearchUserAdminResponse(
                searchUserAdminRequest.getCurrentPage(),
                searchUserAdminRequest.getPageSize(),
                result);
        searchUserAdminResponse.setAll(count.intValue());
        searchUserAdminResponse.setQuery(searchUserAdminRequest.getQuery());
        return searchUserAdminResponse;
    }

    public UserEmailPasswordRecoverDoc createPasswordRecover(String email) throws UserNotExistException{
        return userEmailPasswordRecoveryService.createRecovery(email);
    }

    public Boolean passwordRecovery(ObjectId recoveryId, String code, String password) throws UserExistException{
        UserEmailPasswordRecoverDoc userEmailPasswordRecoverDoc = userEmailPasswordRecoveryService.findByCodeAndId(recoveryId,code);
        if( userEmailPasswordRecoverDoc == null || userEmailPasswordRecoverDoc.getIsRecovered()) return false;

        UserDoc userDoc = getUser(userEmailPasswordRecoverDoc.getUserId());
        userDoc.setPasswordAsHex(password);
        save(userDoc);
        userEmailPasswordRecoverDoc.setIsRecovered(true);
        userEmailPasswordRecoveryService.save(userEmailPasswordRecoverDoc);
        return true;
    }
}
