package com.ub.odnoklassniki.services;

import com.ub.odnoklassniki.models.AppPropertiesOkDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class AppPropertiesOkService {
    @Autowired private MongoTemplate mongoTemplate;

    public AppPropertiesOkDoc getOkProp() {
        AppPropertiesOkDoc appPropertiesOkDoc = mongoTemplate.findById(AppPropertiesOkDoc.STATIC_ID, AppPropertiesOkDoc.class);

        if (appPropertiesOkDoc == null) {
            appPropertiesOkDoc = new AppPropertiesOkDoc();
            save(appPropertiesOkDoc);
        }
        return appPropertiesOkDoc;
    }

    public void save(AppPropertiesOkDoc appPropertiesOkDoc) {
        mongoTemplate.save(appPropertiesOkDoc);
    }
}
