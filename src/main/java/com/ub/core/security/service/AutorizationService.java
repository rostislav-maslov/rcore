package com.ub.core.security.service;

import com.ub.core.user.models.UserDoc;
import com.ub.core.user.models.UserStatusEnum;
import com.ub.core.user.service.UserService;
import com.ub.core.security.service.exceptions.UserNotAutorizedException;
import com.ub.core.security.session.SessionModel;
import com.ub.core.security.session.SessionType;
import com.ub.core.social.vk.support.HttpsConnectionHelper;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;

@Component
public class AutorizationService {

    @Autowired private UserService userService;

    public HttpSession getSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

    public String getTokenEmail(UserDoc userDoc) {
        String t = userDoc.getEmail() + ";" + userDoc.getPassword() + "42";
        return DigestUtils.md5Hex(t);
    }

    public SessionModel getSessionModelEmailType(UserDoc userDoc) {
        SessionModel sessionModel = new SessionModel();
        sessionModel.setIdUser(userDoc.getId());
        sessionModel.setType(SessionType.EMAIL);
        sessionModel.setToken(getTokenEmail(userDoc));
        return sessionModel;
    }
    public SessionModel getSessionModelVkType(UserDoc userDoc, String token) {
        SessionModel sessionModel = new SessionModel();
        sessionModel.setIdUser(userDoc.getId());
        sessionModel.setType(SessionType.VK);
        sessionModel.setToken(token);
        return sessionModel;
    }

    public void logout(){
        HttpSession httpSession = getSession();
        Enumeration enumerator = httpSession.getAttributeNames();
        while (enumerator.hasMoreElements()){
            Object o = enumerator.nextElement();
            httpSession.removeAttribute(o.toString());
        }
        //httpSession.removeAttribute(SessionModel.ID_USER);
        //httpSession.removeAttribute(SessionModel.TOKEN);
        //httpSession.removeAttribute(SessionModel.TYPE);
    }

    public void autorizeEmail(String email, String password) throws UserNotAutorizedException {
        UserDoc userDoc = userService.getUserByEmail(email);
        if (userDoc == null || userDoc.getPassword() == null) throw new UserNotAutorizedException();
        if (!userDoc.getPassword().equals(UserDoc.generateHexPassword(email, password)))
            throw new UserNotAutorizedException();
        SessionModel sessionModel = getSessionModelEmailType(userDoc);
        HttpSession httpSession = getSession();
        httpSession = sessionModel.fillSession(httpSession);
        httpSession.setMaxInactiveInterval(60 * 60 * 24 * 3);
    }

    public void authorizeVk(String accessToken, String userVkId){
        UserDoc userDoc = userService.getUserByVkId(userVkId);

        if (userDoc == null) {
            userDoc = new UserDoc();
            userDoc.setUserVkId(userVkId);
            userDoc.setUserStatus(UserStatusEnum.ACTIVE);
            userService.getUserDocService().save(userDoc);
        }

        SessionModel sessionModel = getSessionModelVkType(userDoc,accessToken);
        HttpSession httpSession = getSession();
        httpSession = sessionModel.fillSession(httpSession);
    }

    public UserDoc getUserFromSession() throws UserNotAutorizedException {
        HttpSession httpSession = getSession();
        SessionModel sessionModel = null;
        try {
            sessionModel = new SessionModel(httpSession);
        } catch (NullPointerException e) {
        }

        if (sessionModel.getType() == null) throw new UserNotAutorizedException();
        if (sessionModel.getToken() == null) throw new UserNotAutorizedException();
        if (sessionModel.getIdUser() == null) throw new UserNotAutorizedException();
        if((sessionModel.getType().equals(SessionType.EMAIL))){
            UserDoc user = userService.getUser(sessionModel.getIdUser());
            if (user == null) throw new UserNotAutorizedException();
            if (sessionModel.getType().equals(SessionType.EMAIL) && getTokenEmail(user).equals(sessionModel.getToken()))
                return user;
        }
        if((sessionModel.getType().equals(SessionType.VK))){
            UserDoc user = userService.getUser(sessionModel.getIdUser());
            if (user == null) throw new UserNotAutorizedException();
            if(checkAccessTokenVk(user) == false){
                throw new UserNotAutorizedException();
            }
            else {
                return user;
            }
        }

        throw new UserNotAutorizedException();
    }

    private Boolean checkAccessTokenVk(UserDoc userDoc){
        HttpSession httpSession = getSession();
        SessionModel sessionModel = null;
        try {
            sessionModel = new SessionModel(httpSession);

            HttpsConnectionHelper helper = new HttpsConnectionHelper();
            String https_url = "https://api.vkontakte.ru/method/isAppUser?uid="+ userDoc.getUserVkId()
                    +"&access_token="+ sessionModel.getToken();
            URL url;
            try {
                url = new URL(https_url);
                HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
                helper.print_https_cert(con);
                String result = helper.print_content(con);
                Map json = (JSONObject)new JSONValue().parse(result);
                if(json.get("response").toString().equals("1")){
                    return true;
                }
                else {
                    return false;
                }
            } catch (MalformedURLException ex) {
                return false;
            } catch (IOException er) {
                return false;
            }
        } catch (NullPointerException e) {

        }

        return false;
    }

}
