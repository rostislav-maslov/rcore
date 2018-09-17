package com.ub.core.file.view;

import com.ub.core.base.search.SearchResponse;

import java.util.List;

public class SearchFileResponse extends SearchResponse {

    private List<FileView> result;

    public SearchFileResponse() {
    }

    public SearchFileResponse(int currentPage, int pageSize, List<FileView> result) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.result = result;
    }

    public List<FileView> getResult() {
        return result;
    }

    public void setResult(List<FileView> result) {
        this.result = result;
    }
}
