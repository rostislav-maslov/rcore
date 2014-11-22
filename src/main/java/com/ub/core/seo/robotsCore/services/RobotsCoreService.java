package com.ub.core.seo.robotsCore.services;

import com.ub.core.seo.robotsCore.models.RobotsCoreDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RobotsCoreService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public RobotsCoreDoc save(RobotsCoreDoc robotsCoreDoc) {
        robotsCoreDoc.setId(RobotsCoreDoc.ID);
        robotsCoreDoc.setLastUpdate(new Date());
        mongoTemplate.save(robotsCoreDoc);
        return robotsCoreDoc;
    }

    public RobotsCoreDoc getRobots() {
        RobotsCoreDoc robotsCoreDoc = mongoTemplate.findById(RobotsCoreDoc.ID, RobotsCoreDoc.class);
        if(robotsCoreDoc == null){
            robotsCoreDoc = new RobotsCoreDoc();
            robotsCoreDoc.setRobots("");
            robotsCoreDoc = save(robotsCoreDoc);
        }
        return robotsCoreDoc;
    }


}
