package com.ub.vk.services;

import com.ub.vk.models.AppPropertiesVkDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class AppPropertiesVkService {
    @Autowired private MongoTemplate mongoTemplate;

    /**
     *
     * @return
     */
    public AppPropertiesVkDoc getVkProp() {
        AppPropertiesVkDoc appPropertiesVkDoc
                = mongoTemplate.findById(AppPropertiesVkDoc.STATIC_ID, AppPropertiesVkDoc.class);
        if(appPropertiesVkDoc == null){
            appPropertiesVkDoc = new AppPropertiesVkDoc();
            save(appPropertiesVkDoc);
        }
        return appPropertiesVkDoc;
    }

    /**
     *
     * @param appPropertiesVkDoc
     */
    public void save(AppPropertiesVkDoc appPropertiesVkDoc) {
        mongoTemplate.save(appPropertiesVkDoc);
    }
}
