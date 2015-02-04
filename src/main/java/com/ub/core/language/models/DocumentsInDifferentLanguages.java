package com.ub.core.language.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class DocumentsInDifferentLanguages {

    private ObjectId id;

    private String classPath;

    private List<DiffDoc> docsIds = new ArrayList<DiffDoc>();

    public void addDiffDoc(LanguageCode languageCode, ObjectId docId){
        if(getDiffDocById(docId) == null){
            docsIds.add(new DiffDoc(languageCode,docId));
        }
    }

    public void deleteDiffDoc(ObjectId docId){
        List<DiffDoc> didlsToDelete = new ArrayList<DiffDoc>();
        for(DiffDoc diffDoc : docsIds){
            if(diffDoc.getDocId().equals(docId)){
                didlsToDelete.add(diffDoc);
            }
        }
        docsIds.removeAll(didlsToDelete);
    }

    public DiffDoc getDiffDocByLang(LanguageCode languageCode){
        for(DiffDoc diffDoc : docsIds){
            if(diffDoc.getLanguageCode().equals(languageCode))
                return diffDoc;
        }
        return null;
    }

    public DiffDoc getDiffDocById(ObjectId id){
        for(DiffDoc diffDoc : docsIds){
            if(diffDoc.getDocId().equals(id))
                return diffDoc;
        }
        return null;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<DiffDoc> getDocsIds() {
        return docsIds;
    }

    public void setDocsIds(List<DiffDoc> docsIds) {
        this.docsIds = docsIds;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }
}
