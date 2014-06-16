package com.ub.vk.response.users.get;

import java.util.List;

public class UsersGetResponse {
    private List<UserInfo> response;

    public List<UserInfo> getResponse() {
        return response;
    }

    public void setResponse(List<UserInfo> response) {
        this.response = response;
    }
}
