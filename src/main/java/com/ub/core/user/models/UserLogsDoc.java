package com.ub.core.user.models;

import com.ub.core.base.models.BaseModel;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * Created by fyzu on 16.09.16.
 */
public class UserLogsDoc extends BaseModel {

    @Id
    private ObjectId id;
    private ObjectId userId;
    private UserLoginStatusEnum status;
    private String ip;
    private String browser;
    private String browserVersion;
    private String operatingSystem;
    private String deviceType;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public UserLoginStatusEnum getStatus() {
        return status;
    }

    public void setStatus(UserLoginStatusEnum status) {
        this.status = status;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
