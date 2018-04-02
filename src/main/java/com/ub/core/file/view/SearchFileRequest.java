package com.ub.core.file.view;

import com.ub.core.base.search.SearchRequest;

public class SearchFileRequest extends SearchRequest {
    public SearchFileRequest() {
    }

    public SearchFileRequest(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public SearchFileRequest(Integer currentPage, Integer pageSize) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }
}
