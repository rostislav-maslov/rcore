package com.ub.core.pages.services;

import com.ub.core.pages.models.PageDoc;
import com.ub.core.pages.models.TagDoc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PageService {
    @Autowired private IPageService pageService;
    @Autowired private TagService tagService;

    public PageDoc getById(String id){
        return pageService.findOne(id);
    }

    public void update(PageDoc pageDoc){
        List<TagDoc> tags = pageDoc.getTags();
        for(TagDoc tagDoc : tags)
            tagService.update(tagDoc);
        pageService.save(pageDoc);
    }

    public void delete(PageDoc pageDoc){
        pageService.delete(pageDoc);
    }

    public List<PageDoc> getAll(){
        Iterator<PageDoc> docIterator = pageService.findAll().iterator();
        List<PageDoc> docs = new ArrayList<PageDoc>();
        while(docIterator.hasNext()){
            docs.add(docIterator.next());
        }
        return docs;
    }
}
