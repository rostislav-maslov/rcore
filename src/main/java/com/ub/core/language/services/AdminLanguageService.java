package com.ub.core.language.services;

import com.ub.core.language.models.LanguageCode;
import com.ub.core.security.service.AutorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class AdminLanguageService {
    @Autowired private AutorizationService autorizationService;
    public static final String HTTP_SESSION_KEY = "AdminLanguageServiceLangCode";

    public LanguageCode getLanguageCode(){
        HttpSession httpSession = autorizationService.getSession();
        Object o = httpSession.getAttribute(HTTP_SESSION_KEY);
        if(o == null){
            return LanguageCode.defaultLanguage;
        }
        if(o instanceof String){
            String code2 = (String)o;
            LanguageCode lc = LanguageCode.getLangbyCode2(code2);
            if(lc != null){
                return lc;
            }
        }
        return LanguageCode.defaultLanguage;
    }

    public void setLanguageCode(String code2){
        HttpSession httpSession = autorizationService.getSession();
        httpSession.setAttribute(HTTP_SESSION_KEY, code2);
    }
}
