package com.ub.core.language.interception;

import com.ub.core.language.models.LanguageCode;
import com.ub.core.language.services.AdminLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by maslov on 01.10.15.
 */
public class AdminLanguageSupportInterception extends HandlerInterceptorAdapter {

    @Autowired private AdminLanguageService adminLanguageService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException {

        LanguageCode lc = adminLanguageService.getLanguageCode();

        Locale locale = StringUtils.parseLocaleString(lc.getCode2());
        if (locale != null && !locale.equals(request.getLocale())) {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            if (localeResolver == null) {
                throw new IllegalStateException("No LocaleResolver found.");
            }
            try {
                localeResolver.setLocale(request, response, locale);
            } catch (IllegalArgumentException e) {

            }
        }
        return true;
    }

}

