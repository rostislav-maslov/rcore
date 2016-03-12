package com.ub.core.base.search;

public class SearchRequest {
    protected String query = "";
    protected Integer currentPage = 0;
    protected Integer pageSize = 30;

    public Integer skip(){
        return currentPage * pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
