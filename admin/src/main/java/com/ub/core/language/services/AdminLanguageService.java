package com.ub.core.language.services;

import com.ub.core.language.models.LanguageCode;
import com.ub.core.security.service.AutorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

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

    public void updateLang(HttpServletRequest request, HttpServletResponse response){
        LanguageCode lc = getLanguageCode();

        Locale locale = StringUtils.parseLocaleString(lc.getCode2());
        if (locale != null ) {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            if (localeResolver == null) {
                throw new IllegalStateException("No LocaleResolver found.");
            }
            try {
                localeResolver.setLocale(request, response, locale);
            } catch (IllegalArgumentException e) {

            }
        }
    }
}
