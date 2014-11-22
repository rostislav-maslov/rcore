package com.ub.core.picture.view.colors;

import com.ub.core.base.search.SearchResponse;
import com.ub.core.picture.models.PictureColorDoc;

import java.util.List;

public class SearchAdminResponse extends SearchResponse {
    private List<PictureColorDoc> result;


    public SearchAdminResponse() {
    }

    public SearchAdminResponse(Integer currentPage, Integer pageSize, List<PictureColorDoc> result) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.result = result;
    }

    public List<PictureColorDoc> getResult() {
        return result;
    }

    public void setResult(List<PictureColorDoc> result) {
        this.result = result;
    }
}
