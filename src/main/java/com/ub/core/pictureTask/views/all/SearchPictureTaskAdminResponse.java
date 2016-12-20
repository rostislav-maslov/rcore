package com.ub.core.pictureTask.views.all;

import com.ub.core.base.search.SearchResponse;
import com.ub.core.pictureTask.models.PictureTaskDoc;

import java.util.List;

public class SearchPictureTaskAdminResponse extends SearchResponse {
    private List<PictureTaskDoc> result;


    public SearchPictureTaskAdminResponse() {
    }

    public SearchPictureTaskAdminResponse(Integer currentPage, Integer pageSize, List<PictureTaskDoc> result) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.result = result;
    }

    public List<PictureTaskDoc> getResult() {
        return result;
    }

    public void setResult(List<PictureTaskDoc> result) {
        this.result = result;
    }
}
