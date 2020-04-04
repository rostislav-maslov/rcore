package com.ub.core.pictureTask.views.all;

import com.ub.core.base.search.SearchRequest;

public class SearchPictureTaskAdminRequest extends SearchRequest {
    public SearchPictureTaskAdminRequest() {
    }

    public SearchPictureTaskAdminRequest(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public SearchPictureTaskAdminRequest(Integer currentPage, Integer pageSize) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

}
