package com.ub.vk.response.users.get;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersGetResponse {
    private List<UserInfo> response;

    public List<UserInfo> getResponse() {
        return response;
    }

    public void setResponse(List<UserInfo> response) {
        this.response = response;
    }
}
