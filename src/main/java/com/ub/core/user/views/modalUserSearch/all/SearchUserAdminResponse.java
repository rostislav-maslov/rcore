package com.ub.core.user.views.modalUserSearch.all;

import com.ub.core.base.search.SearchResponse;
import com.ub.core.user.models.UserDoc;

import java.util.List;

public class SearchUserAdminResponse extends SearchResponse {
    private List<UserDoc> result;


    public SearchUserAdminResponse() {
    }

    public SearchUserAdminResponse(Integer currentPage, Integer pageSize, List<UserDoc> result) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.result = result;
    }

    public List<UserDoc> getResult() {
        return result;
    }

    public void setResult(List<UserDoc> result) {
        this.result = result;
    }
}
