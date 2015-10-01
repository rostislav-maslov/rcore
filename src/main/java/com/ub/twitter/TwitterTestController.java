package com.ub.twitter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

@Controller
public class TwitterTestController {

    private String your_api_key = "9ZgtugNWmfWwFINLCsmx254Vt", your_api_secret = "V3yOIkSRbUgBgHHecAx6A9gQ9Wwbn7jN4yj5HvDuSAX6MNlLbj";
    //RequestToken requestToken;
    //Twitter twitter;

    @ResponseBody
    @RequestMapping(value = "/twitter", method = RequestMethod.GET)
    public String twitter() {
        TwitterFactory factory = new TwitterFactory();
        Twitter twitter = factory.getInstance();
        twitter.setOAuthConsumer(your_api_key, your_api_secret);

        String authUrl = "";
        //request.getSession().setAttribute("twitter", twitter);
        try {

            RequestToken requestToken = twitter.getOAuthRequestToken("http://sapfreelance.com/twitter/resp");
            authUrl = requestToken.getAuthenticationURL();

        } catch (TwitterException e) {

        }


        return (authUrl);
    }

    @ResponseBody
    @RequestMapping(value = "/twitter/resp", method = RequestMethod.GET)
    public String resp(@RequestParam(required = false) String oauth_verifier,
                       @RequestParam(required = false) String oauth_token) throws TwitterException {

        String verifier = oauth_verifier;
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(your_api_key,your_api_secret);
        RequestToken requestToken = new RequestToken(oauth_token,"");

        AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);

        token = accessToken.getToken();
        tokenSecret = accessToken.getTokenSecret();

        User user = null;
        try {
            user = twitter.showUser(twitter.getId());
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        return user.toString();
    }

    private String token, tokenSecret;

    @ResponseBody
    @RequestMapping(value = "/twitter/user", method = RequestMethod.GET)
    public String resp() throws TwitterException {

        AccessToken accessToken = new AccessToken(token, tokenSecret);
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(your_api_key, your_api_secret);
        twitter.setOAuthAccessToken(accessToken);

        User user = null;
        try {
            user = twitter.showUser(twitter.getId());
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        return user.toString();
    }
}
