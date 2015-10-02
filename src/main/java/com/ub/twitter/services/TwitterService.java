package com.ub.twitter.services;

import com.ub.twitter.models.TwitterAppPropertiesDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

@Component
public class TwitterService {

    @Autowired private MongoTemplate mongoTemplate;

    public String authUrl(String url) {
        TwitterFactory factory = new TwitterFactory();
        Twitter twitter = factory.getInstance();
        TwitterAppPropertiesDoc twitterAppPropertiesDoc = this.getTwitter();
        twitter.setOAuthConsumer(twitterAppPropertiesDoc.getYourApiKey(), twitterAppPropertiesDoc.getYourApiSecret());
        String authUrl = "";
        try {
            RequestToken requestToken = twitter.getOAuthRequestToken(url);

            authUrl = requestToken.getAuthenticationURL();
        } catch (TwitterException e) {
        }
        return (authUrl);
    }

    public Twitter twitter(String token, String tokenSecret) {
        AccessToken accessToken = new AccessToken(token, tokenSecret);
        Twitter twitter = new TwitterFactory().getInstance();
        TwitterAppPropertiesDoc twitterAppPropertiesDoc = getTwitter();
        twitter.setOAuthConsumer(twitterAppPropertiesDoc.getYourApiKey(), twitterAppPropertiesDoc.getYourApiSecret());
        twitter.setOAuthAccessToken(accessToken);
        return twitter;
    }

    public User user(String token, String tokenSecret) throws TwitterException {
        Twitter twitter = twitter(token, tokenSecret);
        return twitter.showUser(twitter.getId());
    }

    public AccessToken accesstoken(String oauth_token, String oauth_verifier) throws TwitterException{
        String verifier = oauth_verifier;
        Twitter twitter = new TwitterFactory().getInstance();
        TwitterAppPropertiesDoc twitterAppPropertiesDoc = this.getTwitter();
        twitter.setOAuthConsumer(twitterAppPropertiesDoc.getYourApiKey(),twitterAppPropertiesDoc.getYourApiSecret());

        RequestToken requestToken = new RequestToken(oauth_token,"");

        AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);

        return accessToken;
    }

    public TwitterAppPropertiesDoc getTwitter() {
        TwitterAppPropertiesDoc properties = mongoTemplate.findById(TwitterAppPropertiesDoc.STATIC_ID, TwitterAppPropertiesDoc.class);

        if (properties == null) {
            properties = new TwitterAppPropertiesDoc();
            mongoTemplate.save(properties);
        }

        return properties;
    }

    public void save(TwitterAppPropertiesDoc twitterAppPropertiesDoc) {
        mongoTemplate.save(twitterAppPropertiesDoc);
    }

}
