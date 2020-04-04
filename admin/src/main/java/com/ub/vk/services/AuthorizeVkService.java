package com.ub.vk.services;

import com.ub.core.utils.HttpsUtils;
import com.ub.facebook.response.FBUserInfo;
import com.ub.facebook.statparam.AuthorizeFbStatic;
import com.ub.vk.models.AppPropertiesVkDoc;
import com.ub.vk.response.AccessTokenResponse;
import com.ub.vk.response.users.get.UserInfo;
import com.ub.vk.services.exception.VkNotAuthorizedException;
import com.ub.vk.statparam.AuthorizeVkStatic;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorizeVkService {

    @Autowired private AppPropertiesVkService appPropertiesVkService;

    public AccessTokenResponse getAccessToken(String code) throws VkNotAuthorizedException {
        AppPropertiesVkDoc appPropertiesVkDoc = appPropertiesVkService.getVkProp();
        return getAccessToken(code, appPropertiesVkDoc.getREDIRECT_URI());
    }

    public AccessTokenResponse getAccessToken(String code, String redirectUrl) throws VkNotAuthorizedException {
        HttpsUtils httpsUtils = new HttpsUtils(AuthorizeVkStatic.ACCESS_TOKEN_URL);

        AppPropertiesVkDoc appPropertiesVkDoc = appPropertiesVkService.getVkProp();

        httpsUtils.addParam(AuthorizeVkStatic.P_CLIENT_ID, appPropertiesVkDoc.getAPP_ID());
        httpsUtils.addParam(AuthorizeVkStatic.P_CLIENT_SECRET, appPropertiesVkDoc.getAPP_SECRET());
        httpsUtils.addParam(AuthorizeVkStatic.P_CODE, code);
        httpsUtils.addParam(AuthorizeVkStatic.P_REDIRECT_URL, redirectUrl);

        try {
            String response = httpsUtils.sendGet();
            AccessTokenResponse accessTokenResponse = new ObjectMapper().readValue(response, AccessTokenResponse.class);
            return accessTokenResponse;
        } catch (Exception e) {
            throw new VkNotAuthorizedException();
        }
    }



}
