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

    private String vkId;
    private String vkAccessToken;
    private String vkEmail;

    private String fbId;
    private String fbAccessToken;
    private String fbEmail;



    public static String generateHexPassword(String email, String password) {
        return DigestUtils.md5Hex(email + ";" + password + "42");
    }

    public void setPasswordAsHex(String notHexPassword) {
        this.password = generateHexPassword(email, notHexPassword);
    }

    public boolean containsRole(Role role){
        for(Role r :roles){
            if(r.getId().equals(role.getId()))
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
}
