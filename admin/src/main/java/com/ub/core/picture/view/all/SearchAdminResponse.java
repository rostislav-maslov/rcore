package com.ub.core.picture.view.all;

import com.ub.core.base.search.SearchResponse;
import com.ub.core.picture.models.PictureDoc;

import java.util.List;

public class SearchAdminResponse extends SearchResponse {
    private List<PictureDoc> result;


    public SearchAdminResponse() {
    }

    public SearchAdminResponse(Integer currentPage, Integer pageSize, List<PictureDoc> result) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.result = result;
    }

    public List<PictureDoc> getResult() {
        return result;
    }

    public void setResult(List<PictureDoc> result) {
        this.result = result;
    }
}
