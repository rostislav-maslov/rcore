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
import com.ub.google.response.GoogleUserInfo;
import com.ub.linkedin.response.LinkedinUserInfo;
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
import twitter4j.User;
import twitter4j.auth.AccessToken;

import java.util.*;

@Component
public class UserService {
    private static Map<String, IUserEvent> userEvents = new HashMap<String, IUserEvent>();

    @Autowired protected MongoTemplate mongoTemplate;
    @Autowired protected UserVkService userVkService;
    @Autowired private RoleService roleService;
    @Autowired private UserEmailVerifiedService userEmailVerifiedService;
    @Autowired private UserEmailPasswordRecoveryService userEmailPasswordRecoveryService;
    @Autowired private AutorizationService autorizationService;
    @Autowired private EmailSessionService emailSessionService;

    public static void addUserEvent(IUserEvent iUserEvent) {
        userEvents.put(iUserEvent.getClass().getCanonicalName(), iUserEvent);
    }

    private void callAfterSave(UserDoc userDoc) {
        for (IUserEvent iUserEvent : userEvents.values()) {
            iUserEvent.afterSave(userDoc);
        }
    }

    private void callAfterDelete(UserDoc userDoc) {
        for (IUserEvent iUserEvent : userEvents.values()) {
            iUserEvent.afterDelete(userDoc);
        }
    }

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
        } catch (UserExistException e) {
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

    public UserDoc findByLogin(String login) {
        return mongoTemplate.findOne(new Query(Criteria.where("login").is(login)), UserDoc.class);
    }

    public UserDoc findByEmail(String email) {
        return mongoTemplate.findOne(new Query(Criteria.where("email").is(email)), UserDoc.class);
    }

    public UserDoc findByEmailForLogin(String email) {
        return mongoTemplate.findOne(new Query(Criteria.where("emailForLogin").is(email)), UserDoc.class);
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

        if (userDoc.getId() == null && userDoc.getLogin() != null) {
            UserDoc old = findByLogin(userDoc.getLogin());
            if (old != null) {
                throw new UserExistException();
            }
        }

        if (userDoc.getId() != null && userDoc.getLogin() != null) {
            UserDoc oldDoc = getUser(userDoc.getId());
            if (oldDoc == null) {
                UserDoc old = findByLogin(userDoc.getLogin());
                if (old != null) {
                    throw new UserExistException();
                }
            }
        }

        mongoTemplate.save(userDoc);
        this.callAfterSave(userDoc);
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
            } catch (UserExistException e) {
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
            } catch (UserExistException e) {
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
        userDoc.setEmail(userEmailVerifiedDoc.getEmail().replace(" ", ""));
        userDoc.setPassword(userEmailVerifiedDoc.getPassword());
        userDoc.setUserStatus(userEmailVerifiedDoc.getUserStatus());
        userDoc.setLastName(userEmailVerifiedDoc.getLastName());
        userDoc.setFirstName(userEmailVerifiedDoc.getFirstName());
        userDoc.setSecondName(userEmailVerifiedDoc.getSecondName());
        userDoc.setFullName(userEmailVerifiedDoc.getFullName());

        userDoc = save(userDoc);
        emailSessionService.authorizeUserDoc(userDoc);
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

    public UserDoc createUserByLogin(String login, String password) throws UserExistException {
        UserDoc userDoc = new UserDoc();
        UserDoc check = findByLogin(login);
        if (check != null) {
            throw new UserExistException();
        }
        userDoc.setLogin(login);
        userDoc.setPasswordForLoginAsHex(password);
        save(userDoc);
        return userDoc;
    }

    public UserDoc createUserByLogin(String login, String password, String email) throws UserExistException {
        UserDoc check = findByEmailForLogin(email);
        if (check != null)
            throw new UserExistException();
        UserDoc userDoc = createUserByLogin(login, password);
        userDoc.setEmailForLogin(email);
        save(userDoc);
        return userDoc;
    }

    /**
     * @param email
     * @param password
     * @return
     * @throws UserExistException
     */
    public UserEmailVerifiedDoc createUserByEmailWithVerified(String email, String password) throws UserExistException {
        return createUserByEmailWithVerified(email, password, "", "", "");
    }

    /**
     * @param email
     * @param password
     * @param lastName
     * @param firstName
     * @param secondName
     * @return
     * @throws UserExistException
     */
    public UserEmailVerifiedDoc createUserByEmailWithVerified(String email, String password, String lastName,
                                                              String firstName, String secondName) throws UserExistException {
        UserEmailVerifiedDoc userEmailVerifiedDoc = new UserEmailVerifiedDoc();
        userEmailVerifiedDoc.setEmail(email);
        userEmailVerifiedDoc.setPasswordAsHex(password);
        userEmailVerifiedDoc.setLastName(lastName);
        userEmailVerifiedDoc.setFirstName(firstName);
        userEmailVerifiedDoc.setSecondName(secondName);
        return userEmailVerifiedService.create(userEmailVerifiedDoc);
    }

    public UserEmailVerifiedDoc createUserByEmailWithVerified(String email, String password, String fullName) throws UserExistException {
        UserEmailVerifiedDoc userEmailVerifiedDoc = new UserEmailVerifiedDoc();
        userEmailVerifiedDoc.setEmail(email);
        userEmailVerifiedDoc.setPasswordAsHex(password);
        userEmailVerifiedDoc.setFullName(fullName);

        return userEmailVerifiedService.create(userEmailVerifiedDoc);
    }

    public UserEmailVerifiedDoc createUserByEmailWithVerified(String email, String password, String fullName, ObjectId firstId, ObjectId secondId) throws UserExistException {
        UserEmailVerifiedDoc userEmailVerifiedDoc = new UserEmailVerifiedDoc();
        userEmailVerifiedDoc.setEmail(email);
        userEmailVerifiedDoc.setPasswordAsHex(password);
        userEmailVerifiedDoc.setFullName(fullName);
        userEmailVerifiedDoc.getCustomId().put("firstId", firstId);
        userEmailVerifiedDoc.getCustomId().put("secondId", secondId);

        return userEmailVerifiedService.create(userEmailVerifiedDoc);
    }

    public UserEmailVerifiedDoc createUserByEmailWithVerified(String email, String password, String lastName,
                                                              String firstName) throws UserExistException {

        return createUserByEmailWithVerified(email, password, lastName, firstName, "");
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
        userDoc.setFbEmail(userInfo.getEmail());
        userDoc.setFbId(userInfo.getId());
        userDoc.setUserStatus(UserStatusEnum.ACTIVE);

        String name[] = userInfo.getName().split(" ");

        if (name.length > 0) {
            if (name.length == 1) {
                userDoc.setFirstName(name[0]);
            } else if (name.length > 1) {
                userDoc.setFirstName(name[1]);
                userDoc.setLastName(name[0]);
            }
        }


        save(userDoc);
        return userDoc;
    }

    public UserDoc createUserByGoogle(GoogleUserInfo userInfo) throws UserExistException {
        UserDoc userDoc = new UserDoc();

        UserDoc check = getUserByGoogleId(userInfo.getId());
        if (check != null) {
            throw new UserExistException();
        }

        userDoc.setGoogleAccessToken(userInfo.getAccessToken());
        userDoc.setGoogleEmail(userInfo.getEmail());
        userDoc.setGoogleId(userInfo.getId());

        userDoc.setUserStatus(UserStatusEnum.ACTIVE);

        userDoc.setFirstName(userInfo.getFirst_name());
        userDoc.setLastName(userInfo.getLast_name());

        save(userDoc);
        return userDoc;
    }

    public UserDoc createUserByLinkedin(LinkedinUserInfo userInfo) throws UserExistException {
        UserDoc userDoc = new UserDoc();

        UserDoc check = getUserByLinkedinId(userInfo.getId());
        if (check != null) {
            throw new UserExistException();
        }

        userDoc.setLinkedinId(userInfo.getId());
        userDoc.setLinkedinAccessToken(userInfo.getAccessToken());
        userDoc.setLinkedinEmail(userInfo.getEmailAddress());

        userDoc.setUserStatus(UserStatusEnum.ACTIVE);

        userDoc.setFirstName(userInfo.getFirstName());
        userDoc.setLastName(userInfo.getLastName());

        save(userDoc);
        return userDoc;
    }

    public UserDoc createUserByTwitter(User user, String token, String tokenSecret) throws UserExistException {
        UserDoc check = getUserByTwitterId(String.valueOf(user.getId()));
        if (check != null) {
            throw new UserExistException();
        }
        UserDoc userDoc = new UserDoc();

        userDoc.setTwitterId(String.valueOf(user.getId()));
        userDoc.setTwitterAccessToken(token);
        userDoc.setTwitterSecretToken(tokenSecret);
        userDoc.setTwitterScreenName(user.getScreenName());

        userDoc.setUserStatus(UserStatusEnum.ACTIVE);

        String[] name = user.getName().split(" ");

        String firstName = "", lastName = "";
        if (name.length == 2) {
            firstName = name[1];
            lastName = name[0];
        }

        userDoc.setFirstName(firstName);
        userDoc.setLastName(lastName);

        save(userDoc);
        return userDoc;
    }

    public UserDoc getUserByTwitterId(String id) {
        return mongoTemplate.findOne(new Query(new Criteria("twitterId").is(id)), UserDoc.class);
    }

    public UserDoc updateVkAccessToken(UserDoc userDoc, String accessToken) {
        userDoc.setVkAccessToken(accessToken);
        try {
            save(userDoc);
        } catch (UserExistException e) {
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

    public UserDoc updateGoogleAccessToken(UserDoc userDoc, String accessToken) {
        userDoc.setGoogleAccessToken(accessToken);
        try {
            save(userDoc);
        } catch (UserExistException e) {
            e.printStackTrace();
        }
        return userDoc;
    }

    public UserDoc updateTwitterAccesToken(UserDoc userDoc, AccessToken accessToken) {
        userDoc.setTwitterAccessToken(accessToken.getToken());
        userDoc.setTwitterSecretToken(accessToken.getTokenSecret());
        try {
            save(userDoc);
        } catch (UserExistException e) {
            e.printStackTrace();
        }
        return userDoc;
    }

    public UserDoc updateLinkedinAccessToken(UserDoc userDoc, String accessToken) {
        userDoc.setLinkedinAccessToken(accessToken);
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
        this.callAfterDelete(userDoc);
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
        } catch (UserExistException e) {
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

        if (vkId == null || vkId.equals("")) return null;
        return mongoTemplate.findOne(new Query(Criteria.where("vkId").is(vkId)), UserDoc.class);
    }

    public UserDoc getUserByFbId(String fbId) {
        return mongoTemplate.findOne(new Query(new Criteria("fbId").is(fbId)), UserDoc.class);
    }

    public UserDoc getUserByGoogleId(String googleId) {
        return mongoTemplate.findOne(new Query(new Criteria("googleId").is(googleId)), UserDoc.class);
    }


    public UserDoc getUserByLinkedinId(String linkedinId) {
        return mongoTemplate.findOne(new Query(new Criteria("linkedinId").is(linkedinId)), UserDoc.class);
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
        } catch (UserExistException e) {
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
        searchUserAdminResponse.setAll(count);
        searchUserAdminResponse.setQuery(searchUserAdminRequest.getQuery());
        return searchUserAdminResponse;
    }

    public UserEmailPasswordRecoverDoc createPasswordRecover(String email) throws UserNotExistException {
        return userEmailPasswordRecoveryService.createRecovery(email);
    }

    public Boolean passwordRecovery(ObjectId recoveryId, String code, String password) throws UserExistException {
        UserEmailPasswordRecoverDoc userEmailPasswordRecoverDoc = userEmailPasswordRecoveryService.findByCodeAndId(recoveryId, code);
        if (userEmailPasswordRecoverDoc == null || userEmailPasswordRecoverDoc.getIsRecovered()) return false;

        UserDoc userDoc = getUser(userEmailPasswordRecoverDoc.getUserId());
        userDoc.setPasswordAsHex(password);
        save(userDoc);
        userEmailPasswordRecoverDoc.setIsRecovered(true);
        userEmailPasswordRecoveryService.save(userEmailPasswordRecoverDoc);
        return true;
    }
}
