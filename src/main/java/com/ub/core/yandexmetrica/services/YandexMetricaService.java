package com.ub.core.yandexmetrica.services;

import com.ub.core.yandexmetrica.models.YandexMetricaDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class YandexMetricaService {
    private static final Long STATIC_ID = 1L;
    @Autowired private IYandexMetricaService yandexMetricaService;

    public YandexMetricaDoc get(){
        YandexMetricaDoc doc = yandexMetricaService.findOne(STATIC_ID);
        if(doc == null){
            doc = new YandexMetricaDoc();
            doc.setContent("");
            doc.setId(STATIC_ID);
            yandexMetricaService.save(doc);
        }
        return doc;
    }

    public void update(String content){
        YandexMetricaDoc doc = get();
        doc.setContent(content);
        yandexMetricaService.save(doc);
    }
}
