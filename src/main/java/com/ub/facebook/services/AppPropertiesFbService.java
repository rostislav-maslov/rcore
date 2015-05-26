package com.ub.facebook.services;

import com.ub.facebook.models.AppPropertiesFbDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class AppPropertiesFbService {
    @Autowired private MongoTemplate mongoTemplate;

    public AppPropertiesFbDoc getFbProp() {
        AppPropertiesFbDoc appPropertiesFbDoc = mongoTemplate.findById(AppPropertiesFbDoc.STATIC_ID, AppPropertiesFbDoc.class);

        if (appPropertiesFbDoc == null) {
            appPropertiesFbDoc = new AppPropertiesFbDoc();
            save(appPropertiesFbDoc);
        }
        return appPropertiesFbDoc;
    }

    public void save(AppPropertiesFbDoc appPropertiesFbDoc) {
        mongoTemplate.save(appPropertiesFbDoc);
    }
}
