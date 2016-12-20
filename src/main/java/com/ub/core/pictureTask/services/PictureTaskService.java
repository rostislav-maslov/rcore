package com.ub.core.pictureTask.services;

import com.ub.core.base.utils.ObjectIdUtils;
import com.ub.core.picture.models.PictureDoc;
import com.ub.core.pictureTask.models.PictureTaskDoc;
import com.ub.core.pictureTask.events.IPictureTaskEvent;
import com.ub.core.pictureTask.models.PictureTaskStatus;
import com.ub.core.pictureTask.views.all.SearchPictureTaskAdminRequest;
import com.ub.core.pictureTask.views.all.SearchPictureTaskAdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Component
public class PictureTaskService {
    private static Map<String, IPictureTaskEvent> pictureTaskEvents = new HashMap<String, IPictureTaskEvent>();

    @Autowired private MongoTemplate mongoTemplate;

    public static void addPictureTaskEvent(IPictureTaskEvent iPictureTaskEvent) {
        pictureTaskEvents.put(iPictureTaskEvent.getClass().getCanonicalName(), iPictureTaskEvent);
    }

    public PictureTaskDoc save(PictureTaskDoc pictureTaskDoc) {
        mongoTemplate.save(pictureTaskDoc);
        callAfterSave(pictureTaskDoc);
        return pictureTaskDoc;
    }

    public PictureTaskDoc findById(ObjectId id) {
        return mongoTemplate.findById(id, PictureTaskDoc.class);
    }

    public void remove(ObjectId id) {
        PictureTaskDoc pictureTaskDoc = findById(id);
        if (pictureTaskDoc == null) return;
        mongoTemplate.remove(pictureTaskDoc);
        callAfterDelete(pictureTaskDoc);
    }

    public PictureTaskDoc getNextTask(){
        Criteria criteria = Criteria.where("status").is(PictureTaskStatus.NEW);
        return mongoTemplate.findOne(new Query(criteria), PictureTaskDoc.class);
    }

    public PictureTaskDoc addTask(PictureDoc pictureDoc, Integer width){
        PictureTaskDoc pictureTaskDoc = findByPicture(pictureDoc.getId(), width);

        if(pictureTaskDoc == null){
            pictureTaskDoc = new PictureTaskDoc();
            pictureTaskDoc.setPictureId(pictureDoc.getId());
            pictureTaskDoc.setWidth(width);
            pictureTaskDoc.setStatus(PictureTaskStatus.NEW);
            save(pictureTaskDoc);
        }

        return pictureTaskDoc;
    }

    public PictureTaskDoc findByPicture(ObjectId pictureId, Integer width){
        return mongoTemplate.findOne(
                new Query(
                        Criteria.where("pictureId").is(pictureId)
                        .and("width").is(width)

                ), PictureTaskDoc.class);
    }

    public SearchPictureTaskAdminResponse findAll(SearchPictureTaskAdminRequest searchPictureTaskAdminRequest) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(
                searchPictureTaskAdminRequest.getCurrentPage(),
                searchPictureTaskAdminRequest.getPageSize(),
                sort);

        Criteria criteria = new Criteria();
        if(ObjectIdUtils.tryParseString(searchPictureTaskAdminRequest.getQuery())!= null) {
            criteria = Criteria.where("pictureId").is(ObjectIdUtils.tryParseString(searchPictureTaskAdminRequest.getQuery()));
        }

        Query query = new Query(criteria);
        Long count = mongoTemplate.count(query, PictureTaskDoc.class);
        query = query.with(pageable);

        List<PictureTaskDoc> result = mongoTemplate.find(query, PictureTaskDoc.class);
        SearchPictureTaskAdminResponse searchPictureTaskAdminResponse = new SearchPictureTaskAdminResponse(
                searchPictureTaskAdminRequest.getCurrentPage(),
                searchPictureTaskAdminRequest.getPageSize(),
                result);
        searchPictureTaskAdminResponse.setAll(count);
        searchPictureTaskAdminResponse.setQuery(searchPictureTaskAdminRequest.getQuery());
        return searchPictureTaskAdminResponse;
    }

    private void callAfterSave(PictureTaskDoc pictureTaskDoc) {
        for (IPictureTaskEvent iPictureTaskEvent : pictureTaskEvents.values()) {
            iPictureTaskEvent.afterSave(pictureTaskDoc);
        }
    }

    private void callAfterDelete(PictureTaskDoc pictureTaskDoc) {
        for (IPictureTaskEvent iPictureTaskEvent : pictureTaskEvents.values()) {
            iPictureTaskEvent.afterDelete(pictureTaskDoc);
        }
    }
}
