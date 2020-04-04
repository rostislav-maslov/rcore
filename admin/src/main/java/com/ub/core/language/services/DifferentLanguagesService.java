package com.ub.core.language.services;

import com.ub.core.language.models.DiffDoc;
import com.ub.core.language.models.DocumentsInDifferentLanguages;
import com.ub.core.language.models.LanguageCode;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DifferentLanguagesService {

    public static Boolean useLanguagePackage = false;

    @Autowired private MongoTemplate mongoTemplate;

    public void addNewDoc(ObjectId id, LanguageCode languageCode, Class clazz) {
        if (useLanguagePackage == false)return;

        DocumentsInDifferentLanguages didl = findByDiffDoc(id, clazz);
        if (didl != null && didl.getDiffDocById(id).getLanguageCode().equals(languageCode)) return;

        if(didl != null){
            didl.getDiffDocById(id).setLanguageCode(languageCode);
            save(didl);
            return;
        }

        didl = new DocumentsInDifferentLanguages();
        didl.setClassPath(clazz.getName());
        didl.getDocsIds().add(new DiffDoc(languageCode, id));
        save(didl);
    }

    public void addTo(ObjectId idTo, ObjectId idNew, LanguageCode languageCode, Class clazz) {
        if (useLanguagePackage == false)return;

        DocumentsInDifferentLanguages didl = findByDiffDoc(idTo, clazz);
        didl.addDiffDoc(languageCode, idNew);
        save(didl);
    }

    public void delete(ObjectId idDoc, Class clazz) {
        if (useLanguagePackage == false)return;

        DocumentsInDifferentLanguages didl = findByDiffDoc(idDoc, clazz);
        didl.deleteDiffDoc(idDoc);
        if (didl.getDocsIds() == null || didl.getDocsIds().size() == 0) {
            mongoTemplate.remove(didl);
        } else {
            save(didl);
        }
    }

    public List<DiffDoc> findDiffDoc(ObjectId idDoc, Class clazz) {
        if (useLanguagePackage == false)return new ArrayList<DiffDoc>();

        Criteria criteria = new Criteria().where("docsIds.docId").is(idDoc).and("classPath").is(clazz.getName());
        DocumentsInDifferentLanguages didls = mongoTemplate.findOne(new Query(criteria), DocumentsInDifferentLanguages.class);
        if(didls == null)return new ArrayList<DiffDoc>();
        return didls.getDocsIds();
    }

//    private DocumentsInDifferentLanguages findById(ObjectId id){
//        return mongoTemplate.findById(id, DocumentsInDifferentLanguages.class);
//    }

    public DocumentsInDifferentLanguages findByDiffDoc(ObjectId idDoc, Class clazz) {
        Criteria criteria = new Criteria().where("docsIds.docId").is(idDoc).and("classPath").is(clazz.getName());
        return mongoTemplate.findOne(new Query(criteria), DocumentsInDifferentLanguages.class);
    }

    private DocumentsInDifferentLanguages save(DocumentsInDifferentLanguages documentsInDifferentLanguages) {
        mongoTemplate.save(documentsInDifferentLanguages);
        return documentsInDifferentLanguages;
    }

}
