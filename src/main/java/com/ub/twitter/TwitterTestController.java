package com.ub.twitter;

import com.ub.twitter.services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.auth.AccessToken;

@Controller
public class TwitterTestController {

    private String your_api_key = "9ZgtugNWmfWwFINLCsmx254Vt", your_api_secret = "V3yOIkSRbUgBgHHecAx6A9gQ9Wwbn7jN4yj5HvDuSAX6MNlLbj";

    @Autowired private TwitterService twitterService;

    @ResponseBody
    @RequestMapping(value = "/twitter", method = RequestMethod.GET)
    public String twitter() {
        return twitterService.authUrl("http://sapfreelance.com/twitter/resp");
    }

    @ResponseBody
    @RequestMapping(value = "/twitter/resp", method = RequestMethod.GET)
    public String resp(@RequestParam(required = false) String oauth_verifier,
                       @RequestParam(required = false) String oauth_token) throws TwitterException {

        AccessToken accessToken = twitterService.accesstoken(oauth_token, oauth_verifier);

        token = accessToken.getToken();
        tokenSecret = accessToken.getTokenSecret();

        User user = twitterService.user(token, tokenSecret);

        return user.toString();
    }

    private String token, tokenSecret;

    @ResponseBody
    @RequestMapping(value = "/twitter/user", method = RequestMethod.GET)
    public String resp() throws TwitterException {
        return twitterService.user(token, tokenSecret).toString();
    }
}
