package com.ub.core.pages.services;

import com.ub.core.base.utils.ClassMapping;
import com.ub.core.pages.models.PageDoc;
import com.ub.core.pages.models.TagDoc;
import com.ub.core.pages.models.enums.PageStatus;
import com.ub.core.pages.views.PageView;
import com.ub.core.user.models.UserDoc;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class PageService {
    @Autowired
    private IPageService pageService;
    @Autowired
    private TagService tagService;

    private static ClassMapping<PageDoc, PageView> pageDocPageViewClassMapping = new ClassMapping<PageDoc, PageView>(PageDoc.class, PageView.class);
    private static ClassMapping<PageView, PageDoc> pageViewPageDocClassMapping = new ClassMapping<PageView, PageDoc>(PageView.class, PageDoc.class);

    public PageDoc getById(String id) {
        return pageService.findOne(id);
    }

    public PageView getViewForClient(String id){
        PageDoc pageDoc = getById(id);
        if(pageDoc == null)
            return null;
        return getViewFromDoc(pageDoc);
    }

    public PageView getViewById(String url){
        PageDoc pageDoc = getById(url);
        if(pageDoc == null)
            return new PageView();
        return getViewFromDoc(pageDoc);
    }

    public List<String> getAllStatuses(){
        List<String> s = new ArrayList<String>();
        PageStatus[] ps = PageStatus.values();
        for(PageStatus pageStatus : ps){
            s.add(pageStatus.toString());
        }
        return s;
    }

    public void update(PageDoc pageDoc) {
        List<TagDoc> tags = pageDoc.getTags();
        for (TagDoc tagDoc : tags)
            tagService.update(tagDoc);
        pageService.save(pageDoc);
    }

    public void update(PageView pageView) {
        PageDoc pageDoc = getDocFromView(pageView);
        update(pageDoc);
    }

    public void delete(PageDoc pageDoc) {
        pageService.delete(pageDoc);
    }

    public List<PageDoc> getAll() {
        Iterator<PageDoc> docIterator = pageService.findAll().iterator();
        List<PageDoc> docs = new ArrayList<PageDoc>();
        while (docIterator.hasNext()) {
            docs.add(docIterator.next());
        }
        return docs;
    }

    private PageDoc getDocFromView(PageView pageView) {
        PageDoc result = new PageDoc();
        pageViewPageDocClassMapping.mapping(pageView, result);

        if (pageView.getTagsLine() != null) {
            String tl = pageView.getTagsLine();
            tl = tl.replaceAll(" ", "");
            String[] tagsArr = pageView.getTagsLine().split(",");
            ArrayList<TagDoc> tags = new ArrayList<TagDoc>();
            for (String s : tagsArr) {
                tags.add(new TagDoc(s));
            }
            result.setTags(tags);
        }

        if (pageView.getStatus() != null) {
            result.setStatus(PageStatus.valueOf(pageView.getStatus()));
        }
        if (pageView.getCreatedById() != null) {
            UserDoc userDoc = new UserDoc();
            userDoc.setId(new ObjectId(pageView.getCreatedById()));
            result.setCreatedBy(userDoc);
        }
        if (pageView.getModifiedById() != null) {
            UserDoc userDoc = new UserDoc();
            userDoc.setId(new ObjectId(pageView.getModifiedById()));
            result.setModifiedBy(userDoc);
        }

        return result;
    }

    private PageView getViewFromDoc(PageDoc pageDoc) {
        PageView pageView = new PageView();
        pageDocPageViewClassMapping.mapping(pageDoc, pageView);

        String tl = "";
        if (pageDoc.getTags() != null) {
            ArrayList<String> tags = new ArrayList<String>();
            for (TagDoc tagDoc : pageDoc.getTags()) {
                tags.add(tagDoc.getId());
                tl += ", " + tagDoc.getId();
            }

            pageView.setTags(tags);
            pageView.setTagsLine(tl);
        }

        if (pageDoc.getStatus() != null) {
            pageView.setStatus(pageDoc.getStatus().toString());
        }
        if (pageDoc.getCreatedBy() != null) {
            pageView.setCreatedById(pageDoc.getCreatedBy().getId().toString());
            pageView.setCreatedByName(pageDoc.getCreatedBy().getId().toString());
        }
        if (pageDoc.getModifiedBy() != null) {
            pageView.setModifiedById(pageDoc.getModifiedBy().getId().toString());
            pageView.setModifiedByName(pageDoc.getModifiedBy().getId().toString());
        }
        return pageView;
    }

    public List<PageView> getAllPagesForAdmin() {
        List<PageDoc> pages = getAll();
        List<PageView> pageViews = new ArrayList<PageView>();
        for (PageDoc pageDoc : pages) {
            pageViews.add(getViewFromDoc(pageDoc));
        }
        return pageViews;
    }
}
