package com.ub.facebook.services;

import com.ub.core.security.service.AutorizationService;
import com.ub.core.utils.HttpsUtils;
import com.ub.facebook.models.AppPropertiesFbDoc;
import com.ub.facebook.response.FBAccessTokenResponse;
import com.ub.facebook.response.FBUserInfo;
import com.ub.facebook.statparam.AuthorizeFbStatic;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URL;

@Component
public class AuthorizeFbService {
    @Autowired private AppPropertiesFbService appPropertiesFbService;
    @Autowired private AutorizationService autorizationService;
    @Autowired private FBSessionService fbSessionService;

    public FBAccessTokenResponse getAccessToken(String code) {
        AppPropertiesFbDoc appPropertiesFbDoc = appPropertiesFbService.getFbProp();
        return getAccessToken(code, appPropertiesFbDoc.getREDIRECT_URI());
    }

    public FBAccessTokenResponse getAccessToken(String code, String redirectUri) {
        HttpsUtils httpsUtils = new HttpsUtils(AuthorizeFbStatic.ACCESS_TOKEN_URL);

        AppPropertiesFbDoc appPropertiesFbDoc = appPropertiesFbService.getFbProp();

        httpsUtils.addParam(AuthorizeFbStatic.P_CLIENT_ID, appPropertiesFbDoc.getAPP_ID());
        httpsUtils.addParam(AuthorizeFbStatic.P_CLIENT_SECRET, appPropertiesFbDoc.getAPP_SECRET());
        httpsUtils.addParam(AuthorizeFbStatic.P_CODE, code);
        httpsUtils.addParam(AuthorizeFbStatic.P_REDIRECT_URL, redirectUri);

        try {
            String response = httpsUtils.sendGet();
            FBAccessTokenResponse accessTokenResponse = new ObjectMapper().readValue(response, FBAccessTokenResponse.class);
            return accessTokenResponse;
        } catch (Exception e) {
            return null;
            // TODO: продумать эксепшн
        }
    }

    public FBUserInfo get(String accessToken) {
        HttpsUtils httpsUtils = new HttpsUtils(AuthorizeFbStatic.ME_URL);

        httpsUtils.addParam(AuthorizeFbStatic.P_ACCESS_TOKEN, accessToken);
        try {
            String response = httpsUtils.sendGet();
            FBUserInfo userInfo = new ObjectMapper().readValue(response, FBUserInfo.class);
            userInfo.setAccessToken(accessToken);

            return userInfo;
        } catch (Exception e) {
            return null;
        }
    }

    public InputStream getImage(FBUserInfo userInfo) {
        try {
            URL url = new URL(AuthorizeFbStatic.GRAPH_URL + userInfo.getId() + "/picture?width=350");

            return url.openConnection().getInputStream();
        } catch (Exception e) {
            return null;
        }
    }

    public AppPropertiesFbDoc getProperties() {
        return appPropertiesFbService.getFbProp();
    }
}
