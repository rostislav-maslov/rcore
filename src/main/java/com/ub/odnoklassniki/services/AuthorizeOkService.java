package com.ub.odnoklassniki.services;

import com.ub.odnoklassniki.models.AppPropertiesOkDoc;
import com.ub.odnoklassniki.response.OkAccessTokenResponse;
import com.ub.odnoklassniki.response.OkUserInfo;
import com.ub.odnoklassniki.statparam.AuthorizeOkStatic;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorizeOkService {
    @Autowired private AppPropertiesOkService appPropertiesOkService;

    CloseableHttpClient httpClient;

    public OkAccessTokenResponse getAccessToken(String code, String redirectUri) throws IOException {
        AppPropertiesOkDoc properties = appPropertiesOkService.getOkProp();

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(AuthorizeOkStatic.TOKEN_URL);

        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("code", code));
        params.add(new BasicNameValuePair("client_id", properties.getCLIENT_ID()));
        params.add(new BasicNameValuePair("client_secret", properties.getSECRET_KEY()));
        params.add(new BasicNameValuePair("redirect_uri", redirectUri));
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));


        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        //Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        StringBuffer result = new StringBuffer();
        if (entity != null) {
            InputStream instream = entity.getContent();
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(instream));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    result.append(inputLine);
                }
                in.close();
            } finally {
                instream.close();
            }
        }

        OkAccessTokenResponse accessTokenResponse = new ObjectMapper().readValue(result.toString(), OkAccessTokenResponse.class);
        return accessTokenResponse;
    }

    public OkUserInfo get(String accessToken) {

        AppPropertiesOkDoc appPropertiesOkDoc = appPropertiesOkService.getOkProp();
        String method = "users.getCurrentUser";
        URIBuilder uriBuilder = fromString(AuthorizeOkStatic.ME_URL);
        uriBuilder
                .setParameter("sig", generateSignature(method, accessToken))
                .setParameter("access_token", accessToken)
                .setParameter("application_key", appPropertiesOkDoc.getPUBLIC_KEY())
                .setParameter("method", method);
        httpClient = HttpClientBuilder.create().build();
        try {
            HttpResponse response = httpClient.execute(new HttpGet(uriBuilder.toString()));
            String content = EntityUtils.toString(response.getEntity());
            OkUserInfo userInfo = new ObjectMapper().readValue(content, OkUserInfo.class);
            userInfo.setAccessToken(accessToken);
            return userInfo;
        } catch (Exception e) {
            return null;
        }
    }

    private String generateSignature(String method, String accessToken) {
        AppPropertiesOkDoc appPropertiesOkDoc = appPropertiesOkService.getOkProp();
        StringBuilder params = new StringBuilder();
        params.append("application_key=").append(appPropertiesOkDoc.getPUBLIC_KEY())
                .append("method=").append(method)
                .append(DigestUtils.md5Hex(accessToken + appPropertiesOkDoc.getSECRET_KEY()));

        return DigestUtils.md5Hex(params.toString());
    }

    private URIBuilder fromString(String s) {
        try {
            return new URIBuilder(s);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
