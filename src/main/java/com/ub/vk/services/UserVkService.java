package com.ub.vk.services;

import com.ub.core.utils.HttpsUtils;
import com.ub.vk.response.users.get.UsersGetResponse;
import com.ub.vk.statparam.UserVkStatic;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserVkService {

    public UsersGetResponse get(String id, String accessToken) {
        List<String> ids = new ArrayList<>();
        ids.add(id);
        return get(ids, accessToken);
    }

    public UsersGetResponse get(List<String> ids, String accessToken) {
        HttpsUtils httpsUtils = new HttpsUtils(UserVkStatic.URL_USER_GET);
        httpsUtils.addParam(UserVkStatic.P_USER_IDS, buildUserIds(ids));
        httpsUtils.addParam(UserVkStatic.P_FIELDS, UserVkStatic.V_FIELDS);
        httpsUtils.addParam(UserVkStatic.P_ACCESS_TOKEN, accessToken);
        httpsUtils.addParam(UserVkStatic.P_VERSION, UserVkStatic.V_VERSION);
        try {
            UsersGetResponse usersGetResponse = new ObjectMapper().readValue(httpsUtils.sendGet(), UsersGetResponse.class);
            return usersGetResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new UsersGetResponse();
    }

    public UsersGetResponse get(String id) {
        List<String> ids = new ArrayList<String>();
        ids.add(id);
        return get(ids);
    }

    public UsersGetResponse get(List<String> ids) {
        HttpsUtils httpsUtils = new HttpsUtils(UserVkStatic.URL_USER_GET);
        httpsUtils.addParam(UserVkStatic.P_USER_IDS, buildUserIds(ids));
        httpsUtils.addParam(UserVkStatic.P_FIELDS, UserVkStatic.V_FIELDS);
        try {
            UsersGetResponse usersGetResponse = new ObjectMapper().readValue(httpsUtils.sendGet(), UsersGetResponse.class);
            return usersGetResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new UsersGetResponse();
    }

    private String buildUserIds(List<String> ids) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < ids.size(); i++) {
            stringBuffer.append(ids.get(i));
            if (i < ids.size() - 1) {
                stringBuffer.append(",");
            }
        }
        return stringBuffer.toString();
    }

    public UsersGetResponse me(String accessToken) {
        HttpsUtils httpsUtils = new HttpsUtils(UserVkStatic.URL_USER_GET);

        httpsUtils.addParam(UserVkStatic.P_FIELDS, UserVkStatic.V_FIELDS);
        httpsUtils.addParam(UserVkStatic.P_ACCESS_TOKEN, accessToken);

        try {
            UsersGetResponse usersGetResponse = new ObjectMapper().readValue(httpsUtils.sendGet(), UsersGetResponse.class);
            return usersGetResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
