package com.ub.core.picture.view.all;

import com.ub.core.base.search.SearchRequest;

public class SearchAdminRequest extends SearchRequest{
    public SearchAdminRequest() {
    }

    public SearchAdminRequest(Integer currentPage){
        this.currentPage = currentPage;
    }

    public SearchAdminRequest(Integer currentPage, Integer pageSize){
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

}
