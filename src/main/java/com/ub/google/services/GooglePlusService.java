package com.ub.google.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Person;
import com.ub.core.base.httpResponse.ResourceNotFoundException;
import com.ub.google.models.AppPropertiesGoogleDoc;
import com.ub.google.response.GoogleUserInfo;
import com.ub.google.routes.GooglePlusRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Eduard on 02.10.2015.
 */
@Component
public class GooglePlusService {
    private static final HttpTransport TRANSPORT = new NetHttpTransport();
    private static final JacksonFactory JSON_FACTORY = new JacksonFactory();

    @Autowired private MongoTemplate mongoTemplate;

    public String formAuthUri() {
        AppPropertiesGoogleDoc properties = getGoogleProperties();
        if (properties.getClient_id().isEmpty() || properties.getClient_secret().isEmpty()
                || properties.getRedirect_uri().isEmpty()) {

            //return "/404"; //TODO: page not found exception
            throw new ResourceNotFoundException();
        }

        StringBuilder builder = new StringBuilder(GooglePlusRoutes.AUTH_URL);

        builder.append("?redirect_uri=" + properties.getRedirect_uri())
                .append("&response_type=" + properties.getResponse_type())
                .append("&client_id=" + properties.getClient_id())
                .append("&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile");

        return builder.toString();
    }

    public AppPropertiesGoogleDoc getGoogleProperties() {
        AppPropertiesGoogleDoc properties = mongoTemplate.findById(AppPropertiesGoogleDoc.STATIC_ID, AppPropertiesGoogleDoc.class);

        if (properties == null) {
            properties = new AppPropertiesGoogleDoc();
            mongoTemplate.save(properties);
        }

        return properties;
    }

    public void save(AppPropertiesGoogleDoc appPropertiesGoogleDoc) {
        mongoTemplate.save(appPropertiesGoogleDoc);
    }

    public GoogleTokenResponse getGoogleAccessToken(String code) throws IOException {
        AppPropertiesGoogleDoc properties = getGoogleProperties();
        return getGoogleAccessToken(code, properties.getRedirect_uri());
    }

    public GoogleTokenResponse getGoogleAccessToken(String code, String redirectUri) throws IOException {
        AppPropertiesGoogleDoc properties = getGoogleProperties();

        GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(TRANSPORT, JSON_FACTORY,
                properties.getClient_id(), properties.getClient_secret(), code, redirectUri).execute();

        GoogleCredential credential = new GoogleCredential.Builder()
                .setJsonFactory(JSON_FACTORY)
                .setTransport(TRANSPORT)
                .setClientSecrets(properties.getClient_id(), properties.getClient_secret()).build()
                .setFromTokenResponse(tokenResponse);


        GoogleTokenResponse googleTokenResponse = new GoogleTokenResponse();
        googleTokenResponse.setAccessToken(credential.getAccessToken());
        return googleTokenResponse;

    }


    public GoogleUserInfo getGooglePersonInfo(GoogleTokenResponse googleTokenResponse) {
        try {
            AppPropertiesGoogleDoc properties = getGoogleProperties();


            GoogleCredential credential = new GoogleCredential.Builder()
                    .setJsonFactory(JSON_FACTORY)
                    .setTransport(TRANSPORT)
                    .setClientSecrets(properties.getClient_id(), properties.getClient_secret()).build()
                    .setAccessToken(googleTokenResponse.getAccessToken());


            Plus service = new Plus.Builder(TRANSPORT, JSON_FACTORY, credential)
                    .setApplicationName(properties.getApplication_name())
                    .build();

            Person person = service.people().get("me").execute();

            return new GoogleUserInfo(person, googleTokenResponse);
        } catch (IOException e) {
            return null;
        }
    }

}
