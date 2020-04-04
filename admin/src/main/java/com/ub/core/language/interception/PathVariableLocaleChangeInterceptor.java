package com.ub.core.language.interception;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

public class PathVariableLocaleChangeInterceptor extends HandlerInterceptorAdapter {

    public static final String DEFAULT_PARAM_NAME = "lang";
    private String paramName = DEFAULT_PARAM_NAME;

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamName() {
        return this.paramName;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException {

        Locale locale = extractLocale(request);
        if(locale != null && !locale.equals(request.getLocale())) {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            if (localeResolver == null) {
                throw new IllegalStateException("No LocaleResolver found.");
            }
            try {
                localeResolver.setLocale(request, response, locale);
            }catch(IllegalArgumentException e){

            }
        }
        return true;
    }

    private Locale extractLocale(HttpServletRequest request) {
        String newLocale = extractLocaleString(request);
        if(StringUtils.hasText(newLocale)) {
            return StringUtils.parseLocaleString(newLocale);
        }
        return request.getLocale();
    }

    @SuppressWarnings({"rawtypes"})
    private String extractLocaleString(HttpServletRequest request) {
        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String newLocale = null;
        if(pathVariables!=null && pathVariables.size()>0&&pathVariables.containsKey(getParamName())) {
            newLocale = (String)pathVariables.get(getParamName());
        }
        if(!StringUtils.hasText(newLocale)) {
            newLocale = request.getParameter(getParamName());
        }
        return newLocale;
    }
}
