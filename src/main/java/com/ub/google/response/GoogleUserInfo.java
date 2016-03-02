package com.ub.google.response;

import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.services.plus.model.Person;

/**
 * Created by Eduard on 02.10.2015.
 */
public class GoogleUserInfo {

    private String id;
    private String email;
    private String first_name;
    private String last_name;
    private String name;
    private String gender;
    private String accessToken;
    private String updated_time="";
    private String verified="";
    private String img_url;

    public GoogleUserInfo() {
    }

    public GoogleUserInfo(Person person, GoogleTokenResponse token) {
        this.id = person.getId();
        if (person.getEmails() != null && person.getEmails().size() > 0) {
            this.email = person.getEmails().get(0).getValue();
        }
        this.first_name = person.getName().getGivenName();
        this.last_name = person.getName().getFamilyName();
        this.name = person.getDisplayName();
        this.gender = person.getGender();
        this.accessToken = token.getAccessToken();
        if (token.getExpiresInSeconds() != null)
            this.updated_time = token.getExpiresInSeconds().toString();
        if (person.getVerified() != null)
            this.verified = person.getVerified().toString();
        if(person.getImage().getUrl()!=null){
            this.img_url=person.getImage().getUrl();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
