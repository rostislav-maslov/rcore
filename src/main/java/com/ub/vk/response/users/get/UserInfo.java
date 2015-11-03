package com.ub.vk.response.users.get;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {
    private String uid;
    private String first_name;
    private String last_name;
    private String sex;
    private String domain;
    private String screen_name;
    private String bdate;
    private Object city;
    private Object country;
    private String timezone;
    private String photo_50;
    private String photo_100;
    private String photo_200;
    private String photo_max;
    private String photo_200_orig;
    private String photo_400_orig;
    private String photo_max_orig;
    private String has_mobile;
    private String online;
    private String can_post;
    private String can_see_all_posts;
    private String can_see_audiov;
    private String can_write_private_message;
    private String mobile_phone;
    private String home_phone;
    private String twitter;
    private String site;
    private String status;
    private Object last_seen;
    private String common_count;
    private Object counters;
    private String university;
    private String university_name;
    private String faculty;
    private String faculty_name;
    private String graduation;
    private String education_form;
    private String education_status;
    private String relation;
    private Object relation_partner;
    private Object universities;
    private Object schools;
    private Object relatives;
    private Object can_see_audio;
    private Object hidden;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Object getHidden() {
        return hidden;
    }

    public void setHidden(Object hidden) {
        this.hidden = hidden;
    }

    public Object getCan_see_audio() {
        return can_see_audio;
    }

    public void setCan_see_audio(Object can_see_audio) {
        this.can_see_audio = can_see_audio;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getPhoto_50() {
        return photo_50;
    }

    public void setPhoto_50(String photo_50) {
        this.photo_50 = photo_50;
    }

    public String getPhoto_100() {
        return photo_100;
    }

    public void setPhoto_100(String photo_100) {
        this.photo_100 = photo_100;
    }

    public String getPhoto_200() {
        return photo_200;
    }

    public void setPhoto_200(String photo_200) {
        this.photo_200 = photo_200;
    }

    public String getPhoto_max() {
        return photo_max;
    }

    public void setPhoto_max(String photo_max) {
        this.photo_max = photo_max;
    }

    public String getPhoto_200_orig() {
        return photo_200_orig;
    }

    public void setPhoto_200_orig(String photo_200_orig) {
        this.photo_200_orig = photo_200_orig;
    }

    public String getPhoto_400_orig() {
        return photo_400_orig;
    }

    public void setPhoto_400_orig(String photo_400_orig) {
        this.photo_400_orig = photo_400_orig;
    }

    public String getPhoto_max_orig() {
        return photo_max_orig;
    }

    public void setPhoto_max_orig(String photo_max_orig) {
        this.photo_max_orig = photo_max_orig;
    }

    public String getHas_mobile() {
        return has_mobile;
    }

    public void setHas_mobile(String has_mobile) {
        this.has_mobile = has_mobile;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getCan_post() {
        return can_post;
    }

    public void setCan_post(String can_post) {
        this.can_post = can_post;
    }

    public String getCan_see_all_posts() {
        return can_see_all_posts;
    }

    public void setCan_see_all_posts(String can_see_all_posts) {
        this.can_see_all_posts = can_see_all_posts;
    }

    public String getCan_see_audiov() {
        return can_see_audiov;
    }

    public void setCan_see_audiov(String can_see_audiov) {
        this.can_see_audiov = can_see_audiov;
    }

    public String getCan_write_private_message() {
        return can_write_private_message;
    }

    public void setCan_write_private_message(String can_write_private_message) {
        this.can_write_private_message = can_write_private_message;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getHome_phone() {
        return home_phone;
    }

    public void setHome_phone(String home_phone) {
        this.home_phone = home_phone;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getLast_seen() {
        return last_seen;
    }

    public void setLast_seen(Object last_seen) {
        this.last_seen = last_seen;
    }

    public String getCommon_count() {
        return common_count;
    }

    public void setCommon_count(String common_count) {
        this.common_count = common_count;
    }

    public Object getCounters() {
        return counters;
    }

    public void setCounters(Object counters) {
        this.counters = counters;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getUniversity_name() {
        return university_name;
    }

    public void setUniversity_name(String university_name) {
        this.university_name = university_name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public void setFaculty_name(String faculty_name) {
        this.faculty_name = faculty_name;
    }

    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation;
    }

    public String getEducation_form() {
        return education_form;
    }

    public void setEducation_form(String education_form) {
        this.education_form = education_form;
    }

    public String getEducation_status() {
        return education_status;
    }

    public void setEducation_status(String education_status) {
        this.education_status = education_status;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Object getRelation_partner() {
        return relation_partner;
    }

    public void setRelation_partner(Object relation_partner) {
        this.relation_partner = relation_partner;
    }

    public Object getUniversities() {
        return universities;
    }

    public void setUniversities(Object universities) {
        this.universities = universities;
    }

    public Object getSchools() {
        return schools;
    }

    public void setSchools(Object schools) {
        this.schools = schools;
    }

    public Object getRelatives() {
        return relatives;
    }

    public void setRelatives(Object relatives) {
        this.relatives = relatives;
    }
}
