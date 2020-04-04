package com.ub.linkedin.services;

import com.ub.linkedin.models.AppPropertiesLinkedinDoc;
import com.ub.linkedin.models.LinkedAccessTokenResponse;
import com.ub.linkedin.response.LinkedinUserInfo;
import com.ub.linkedin.routes.LinkedinRoutes;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduard on 02.10.2015.
 */
@Component
public class LinkedinService {
    @Autowired private MongoTemplate mongoTemplate;

    public AppPropertiesLinkedinDoc getLinkedProperties() {
        AppPropertiesLinkedinDoc properties = mongoTemplate.findById(AppPropertiesLinkedinDoc.STATIC_ID, AppPropertiesLinkedinDoc.class);

        if (properties == null) {
            properties = new AppPropertiesLinkedinDoc();
            mongoTemplate.save(properties);
        }

        return properties;
    }

    public void save(AppPropertiesLinkedinDoc appPropertiesLinkedinDoc) {
        mongoTemplate.save(appPropertiesLinkedinDoc);
    }

    public String formAuthUri() {
        AppPropertiesLinkedinDoc properties = getLinkedProperties();
        if (properties.getClient_id().isEmpty() || properties.getClient_secret().isEmpty()
                || properties.getRedirect_uri().isEmpty() || properties.getState().isEmpty()) {
            return "/404"; //TODO нормально обработать с not found
        }

        StringBuilder builder = new StringBuilder(LinkedinRoutes.AUTH_URL);

        builder.append("?response_type=" + properties.getResponse_type())
                .append("&client_id=" + properties.getClient_id())
                .append("&redirect_uri=" + properties.getRedirect_uri())
                .append("&state=" + properties.getState());

        return builder.toString();
    }

  /**  public LinkedinUserInfo getUserInfo(String code, String state) throws Exception {


        return getUserInfo(accessTokenResponse.getAccess_token());
    }    */

    public LinkedAccessTokenResponse getAccessToken(String code, String state) throws Exception {
        AppPropertiesLinkedinDoc properties = getLinkedProperties();

        // check xss
        if (!state.equals(properties.getState())) {
            return null;
        }

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(LinkedinRoutes.TOKEN_URL);

        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));
        params.add(new BasicNameValuePair("code", code));
        params.add(new BasicNameValuePair("redirect_uri", properties.getRedirect_uri()));
        params.add(new BasicNameValuePair("client_id", properties.getClient_id()));
        params.add(new BasicNameValuePair("client_secret", properties.getClient_secret()));
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

        LinkedAccessTokenResponse accessTokenResponse = new ObjectMapper().readValue(result.toString(), LinkedAccessTokenResponse.class);
        return accessTokenResponse;
    }

    public LinkedAccessTokenResponse getAccessToken(String code, String state,String redirectUri) throws Exception {
        AppPropertiesLinkedinDoc properties = getLinkedProperties();

        // check xss
        if (!state.equals(properties.getState())) {
            return null;
        }

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(LinkedinRoutes.TOKEN_URL);

        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));
        params.add(new BasicNameValuePair("code", code));
        params.add(new BasicNameValuePair("redirect_uri", redirectUri));
        params.add(new BasicNameValuePair("client_id", properties.getClient_id()));
        params.add(new BasicNameValuePair("client_secret", properties.getClient_secret()));
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

        LinkedAccessTokenResponse accessTokenResponse = new ObjectMapper().readValue(result.toString(), LinkedAccessTokenResponse.class);
        return accessTokenResponse;
    }


    public LinkedinUserInfo getUserInfo(String token) throws Exception {
        String url = LinkedinRoutes.GET_USER_URL;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("Authorization", "Bearer " + token);
        con.setRequestProperty("Content-Type", "text/html; charset=utf-8");


        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        LinkedinUserInfo userInfo = new ObjectMapper().readValue(response.toString(), LinkedinUserInfo.class);

        userInfo.setAccessToken(token);

        return userInfo;
    }
}
