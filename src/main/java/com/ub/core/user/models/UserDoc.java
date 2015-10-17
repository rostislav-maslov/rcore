package com.ub.core.user.models;


import com.ub.core.base.role.Role;
import org.apache.commons.codec.digest.DigestUtils;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Document
public class UserDoc {
    @Id
    protected ObjectId id;

    protected Set<Role> roles = new HashSet<Role>();

    protected String email;
    protected String password;
    protected UserStatusEnum userStatus = UserStatusEnum.ACTIVE;

    protected String firstName = "";
    protected String lastName = "";
    /**
     * отчество
     */
    protected String secondName = "";

    private String vkId;
    private String vkAccessToken;
    private String vkEmail;

    private String fbId;
    private String fbAccessToken;
    private String fbEmail;

    private String googleId;
    private String googleAccessToken;
    private String googleEmail;

    private String linkedinId;
    private String linkedinAccessToken;
    private String linkedinEmail;

    private String twitterId;
    private String twitterAccessToken;
    private String twitterSecretToken;
    private String twitterEmail;
    private String twitterScreenName;

    private String login;
    private String passwordForLogin;
    private String emailForLogin;

    public static String generateHexPassword(String email, String password) {
        return DigestUtils.md5Hex(email + ";" + password + "42");
    }

    public void setPasswordAsHex(String notHexPassword) {
        this.password = generateHexPassword(email, notHexPassword);
    }

    public void setPasswordForLoginAsHex(String notHexPassword) {
        this.passwordForLogin = generateHexPassword(login, notHexPassword);
    }

    public boolean containsRole(Role role) {
        for (Role r : roles) {
            if (r.getId().equals(role.getId()))
                return true;
        }
        return false;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatusEnum getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatusEnum userStatus) {
        this.userStatus = userStatus;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getVkId() {
        return vkId;
    }

    public void setVkId(String vkId) {
        this.vkId = vkId;
    }

    public String getVkAccessToken() {
        return vkAccessToken;
    }

    public void setVkAccessToken(String vkAccessToken) {
        this.vkAccessToken = vkAccessToken;
    }

    public String getVkEmail() {
        return vkEmail;
    }

    public void setVkEmail(String vkEmail) {
        this.vkEmail = vkEmail;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getFbAccessToken() {
        return fbAccessToken;
    }

    public void setFbAccessToken(String fbAccessToken) {
        this.fbAccessToken = fbAccessToken;
    }

    public String getFbEmail() {
        return fbEmail;
    }

    public void setFbEmail(String fbEmail) {
        this.fbEmail = fbEmail;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordForLogin() {
        return passwordForLogin;
    }

    public void setPasswordForLogin(String passwordForLogin) {
        this.passwordForLogin = passwordForLogin;
    }

    public String getEmailForLogin() {
        return emailForLogin;
    }

    public void setEmailForLogin(String emailForLogin) {
        this.emailForLogin = emailForLogin;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getGoogleAccessToken() {
        return googleAccessToken;
    }

    public void setGoogleAccessToken(String googleAccessToken) {
        this.googleAccessToken = googleAccessToken;
    }

    public String getGoogleEmail() {
        return googleEmail;
    }

    public void setGoogleEmail(String googleEmail) {
        this.googleEmail = googleEmail;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    public String getTwitterAccessToken() {
        return twitterAccessToken;
    }

    public void setTwitterAccessToken(String twitterAccessToken) {
        this.twitterAccessToken = twitterAccessToken;
    }

    public String getTwitterEmail() {
        return twitterEmail;
    }

    public void setTwitterEmail(String twitterEmail) {
        this.twitterEmail = twitterEmail;
    }

    public String getTwitterSecretToken() {
        return twitterSecretToken;
    }

    public void setTwitterSecretToken(String twitterSecretToken) {
        this.twitterSecretToken = twitterSecretToken;
    }

    public String getTwitterScreenName() {
        return twitterScreenName;
    }

    public void setTwitterScreenName(String twitterScreenName) {
        this.twitterScreenName = twitterScreenName;
    }

    public String getLinkedinId() {
        return linkedinId;
    }

    public void setLinkedinId(String linkedinId) {
        this.linkedinId = linkedinId;
    }

    public String getLinkedinAccessToken() {
        return linkedinAccessToken;
    }

    public void setLinkedinAccessToken(String linkedinAccessToken) {
        this.linkedinAccessToken = linkedinAccessToken;
    }

    public String getLinkedinEmail() {
        return linkedinEmail;
    }

    public void setLinkedinEmail(String linkedinEmail) {
        this.linkedinEmail = linkedinEmail;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
