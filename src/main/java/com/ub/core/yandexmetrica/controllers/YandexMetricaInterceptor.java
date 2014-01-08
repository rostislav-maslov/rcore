package com.ub.core.yandexmetrica.controllers;

import com.ub.core.yandexmetrica.services.YandexMetricaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class YandexMetricaInterceptor extends HandlerInterceptorAdapter{
    @Autowired private YandexMetricaService yandexMetricaService;
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("yandexMetricaScript", yandexMetricaService.get().getContent());
    }
}
