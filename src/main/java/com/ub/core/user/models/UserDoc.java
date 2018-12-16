package com.ub.core.user.models;


import com.ub.core.base.role.Role;
import org.apache.commons.codec.digest.DigestUtils;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

import javax.persistence.Id;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.*;

@Document
@CompoundIndexes({
        @CompoundIndex(
                name = "find_by_accesstoken_new",
                def = "{'accessTokens.token': 1}"
        ),
        @CompoundIndex(
                name = "find_by_refreshtoken_new",
                def = "{'refreshTokens.token': 1}"
        ),
        @CompoundIndex(
                name = "find_by_phone",
                def = "{'phoneNumber': 1}"
        ),
        @CompoundIndex(
                name = "find_by_email",
                def = "{'email': 1}"
        )
})
public class UserDoc {

    public static String generateHexPassword(String email, String password) {
        return DigestUtils.md5Hex(email + ";" + password + "42");
    }

    public static UserToken generateNewToken(UserDoc userDoc) {
        UserToken userToken = new UserToken();
        userToken.setToken(new ObjectId().toString() + userDoc.getId() + new ObjectId().toString());
        return userToken;
    }

    @Id
    protected ObjectId id;

    protected Set<Role> roles = new HashSet<Role>();

    protected UserStatusEnum userStatus = UserStatusEnum.ACTIVE;

    protected String firstName = "";
    protected String lastName = "";


    private Long phoneNumber;
    private String passwordPhone;

    protected String email;
    protected String password;

    /**
     * отчество
     */
    protected String secondName = "";
    protected String fullName = "";

    private String vkId;
    private String vkAccessToken;
    private String vkEmail;

    private String okId;
    private String okAccessToken;
    private String okEmail;

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

    private Integer fails = 0;
    private Date lastFailDate = new Date();

    private List<UserToken> accessTokens = new ArrayList<UserToken>();
    private List<UserToken> refreshTokens = new ArrayList<UserToken>();

    public Boolean checkAccessToken(String token) {
        for (UserToken userToken : this.accessTokens) {
            if (userToken.getToken().equals(token)) {
                return userToken.isActive();
            }
        }

        return false;
    }

    public Boolean checkRefreshToken(String token) {
        for (UserToken userToken : this.refreshTokens) {
            if (userToken.getToken().equals(token)) {
                return userToken.isActive();
            }
        }

        return false;
    }

    public UserToken getUserTokenByString(String token) {
        for (UserToken userToken : this.accessTokens) {
            if (userToken.getToken().equals(token) && userToken.isActive()) {
                return userToken;
            }
        }

        return null;
    }

    public UserToken getActiveAccessToken() {
        for (UserToken userToken : this.accessTokens) {
            if (userToken.isActive()) {
                return userToken;
            }
        }
        return null;
    }

    public UserToken getActiveRefreshToken() {
        for (UserToken userToken : this.refreshTokens) {
            if (userToken.isActive()) {
                return userToken;
            }
        }
        return null;
    }

    public String getNoNullEmail() {
        if (email != null) {
            return email;
        } else if (emailForLogin != null) {
            return emailForLogin;
        } else if (vkEmail != null) {
            return vkEmail;
        } else if (fbEmail != null) {
            return fbEmail;
        } else if (twitterEmail != null) {
            return twitterEmail;
        } else if (linkedinEmail != null) {
            return linkedinEmail;
        } else if (googleEmail != null) {
            return googleEmail;
        } else if (okEmail != null) {
            return okEmail;
        }

        return null;
    }

    public void addAccessToken(UserToken userToken) {
        this.accessTokens.add(userToken);
    }

    public void addRefreshToken(UserToken userToken) {
        this.refreshTokens.add(userToken);
    }

    public void setPasswordAsHex(String notHexPassword) {
        this.password = generateHexPassword(email, notHexPassword);
    }

    public void setPasswordForLoginAsHex(String notHexPassword) {
        this.passwordForLogin = generateHexPassword(login, notHexPassword);
    }

    public void setPasswordPhoneAsHex(String notHexPassword) {
        this.passwordPhone = generateHexPassword(phoneNumber.toString(), notHexPassword);
    }

    public boolean containsRole(Role role) {
        for (Role r : roles) {
            if (r.getId().equals(role.getId()))
                return true;
        }
        return false;
    }

    public boolean needLink(UserDoc userDoc) {
        return ((!StringUtils.isEmpty(userDoc.getVkId())
                || !StringUtils.isEmpty(userDoc.getFbId())
                || !StringUtils.isEmpty(userDoc.getEmail()))
                && userDoc.getPhoneNumber() == null);
    }

    public String getPasswordPhone() {
        return passwordPhone;
    }

    public void setPasswordPhone(String passwordPhone) {
        this.passwordPhone = passwordPhone;
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

    public Integer getFails() {
        return fails;
    }

    public void setFails(Integer fails) {
        this.fails = fails;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<UserToken> getAccessTokens() {
        return accessTokens;
    }

    public void setAccessTokens(List<UserToken> accessTokens) {
        this.accessTokens = accessTokens;
    }

    public List<UserToken> getRefreshTokens() {
        return refreshTokens;
    }

    public void setRefreshTokens(List<UserToken> refreshTokens) {
        this.refreshTokens = refreshTokens;
    }

    public Date getLastFailDate() {
        return lastFailDate;
    }

    public void setLastFailDate(Date lastFailDate) {
        this.lastFailDate = lastFailDate;
    }

    public String getOkId() {
        return okId;
    }

    public void setOkId(String okId) {
        this.okId = okId;
    }

    public String getOkAccessToken() {
        return okAccessToken;
    }

    public void setOkAccessToken(String okAccessToken) {
        this.okAccessToken = okAccessToken;
    }

    public String getOkEmail() {
        return okEmail;
    }

    public void setOkEmail(String okEmail) {
        this.okEmail = okEmail;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
