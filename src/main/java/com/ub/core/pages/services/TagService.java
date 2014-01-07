package com.ub.core.pages.services;

import com.ub.core.pages.models.TagDoc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TagService {
    @Autowired private ITagService tagService;

    public TagDoc getById(String id){
        return tagService.findOne(id);
    }

    public void update(TagDoc pageDoc){
        tagService.save(pageDoc);
    }

    public void delete(TagDoc pageDoc){
        tagService.delete(pageDoc);
    }

    public List<TagDoc> getAll(){
        Iterator<TagDoc> docIterator = tagService.findAll().iterator();
        List<TagDoc> docs = new ArrayList<TagDoc>();
        while(docIterator.hasNext()){
            docs.add(docIterator.next());
        }
        return docs;
    }
}
