package com.ub.core.user.views.modalUserSearch.all;

import com.ub.core.base.search.SearchRequest;

public class SearchUserAdminRequest extends SearchRequest{
    public SearchUserAdminRequest() {
    }

    public SearchUserAdminRequest(Integer currentPage){
        this.currentPage = currentPage;
    }

    public SearchUserAdminRequest(Integer currentPage, Integer pageSize){
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

}
