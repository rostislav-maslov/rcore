package com.ub.core.user.service;

import com.google.common.collect.Lists;
import com.ub.core.base.role.Role;
import com.ub.core.base.role.RoleBoost;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.models.UserStatusEnum;
import com.ub.core.user.service.exceptions.UserExistException;
import com.ub.core.user.service.exceptions.UserNotExistException;
import com.ub.core.user.views.AddEditUserView;
import com.ub.core.user.views.modalUserSearch.all.SearchUserAdminRequest;
import com.ub.core.user.views.modalUserSearch.all.SearchUserAdminResponse;
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

    /**
     * Блокировка пользотеля
     *
     * @param id
     */
    public void block(ObjectId id) {
        UserDoc userDoc = mongoTemplate.findById(id, UserDoc.class);
        userDoc.setUserStatus(UserStatusEnum.BLOCK);
        mongoTemplate.save(userDoc);
    }

    /**
     * Активация пользователя
     *
     * @param id
     */
    public void active(ObjectId id) {
        UserDoc userDoc = mongoTemplate.findById(id, UserDoc.class);
        userDoc.setUserStatus(UserStatusEnum.ACTIVE);
        mongoTemplate.save(userDoc);
    }

    public UserDoc findByEmail(String email){
        return mongoTemplate.findOne(new Query(Criteria.where("email").is(email)), UserDoc.class);
    }

    public void addRoleToUser(ObjectId idUser, Role role){
        UserDoc userDoc = mongoTemplate.findById(idUser, UserDoc.class);
        if(userDoc != null){
            userDoc.getRoles().add(role);
            mongoTemplate.save(userDoc);
        }
    }

    /**
     * Создание нового пользователя по email
     *
     * @param addEditUserView
     * @throws UserExistException
     */
    public void createUserByEmail(AddEditUserView addEditUserView) throws UserExistException {
        UserDoc userDoc = new UserDoc();

        UserDoc check = findByEmail(addEditUserView.getEmail());
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
        mongoTemplate.save(userDoc);
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
        if(usersGetResponse != null && usersGetResponse.getResponse()!=null && usersGetResponse.getResponse().size()==1){
            UserInfo userInfo = usersGetResponse.getResponse().get(0);

            userDoc.setFirstName(userInfo.getFirst_name());
            userDoc.setLastName(userInfo.getLast_name());
        }

        mongoTemplate.save(userDoc);
        return userDoc;
    }

    public UserDoc updateVkAccessToken(UserDoc userDoc, String accessToken){
        userDoc.setVkAccessToken(accessToken);
        mongoTemplate.save(accessToken);
        return userDoc;
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

        mongoTemplate.save(userDoc);
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

    public UserDoc getUserByVkId(String vkId){
        return mongoTemplate.findOne(new Query(Criteria.where("vkId").is(vkId)), UserDoc.class);
    }

    public String restorePassword(String email) throws UserNotExistException{
        String pass = DigestUtils.md2Hex(new Date().toString());
        UserDoc userDoc = getUserByEmail(email);
        if(userDoc == null)
            throw new UserNotExistException();
        userDoc.setPasswordAsHex(pass);
        mongoTemplate.save(userDoc);
        return pass;
    }

    /**
     *
     * @param searchUserAdminRequest
     * @return
     */
    public SearchUserAdminResponse findAll(SearchUserAdminRequest searchUserAdminRequest) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(
                searchUserAdminRequest.getCurrentPage(),
                searchUserAdminRequest.getPageSize(),
                sort);

        Criteria criteria =new Criteria();
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
}
