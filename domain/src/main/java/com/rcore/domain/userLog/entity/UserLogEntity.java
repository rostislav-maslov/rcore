package com.rcore.domain.userLog.entity;

import com.rcore.domain.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class UserLogEntity extends BaseEntity {
    public static class DeviceData {
        protected String ip = null;
        protected String deviceType = null;

        protected String browser = null;
        protected String browserVersion = null;
        protected String operatingSystem = null;

    }

    public enum Status {
        SUCCESS, FAIL;
    }

    protected String id;
    protected String userId = null;
    protected Status status = null;
    protected DeviceData deviceData = new DeviceData();
}
