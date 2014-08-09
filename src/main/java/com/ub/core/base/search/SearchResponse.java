package com.ub.core.base.search;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse {
    protected Integer currentPage = 0;
    protected Integer pageSize = 10;
    protected Integer all = 0;
    protected String query = "";

    public Integer prevNum(Integer all) {
        this.all = all;
        return prevNum();
    }

    public Integer prevNum() {
        if (currentPage - 1 >= 0)
            return currentPage - 1;
        else
            return 0;
    }

    public Integer nextNum(Integer all) {
        this.all = all;
        return nextNum();
    }

    public Integer nextNum() {
        Integer allPages = all/pageSize;
        if(allPages == 0){
            return 0;
        }

        if(all%pageSize != 0)allPages++;

        if (currentPage + 1 <= allPages - 1)
            return currentPage + 1;
        else
            return allPages - 1;
    }

    public List<Integer> paginator() {
        Integer start, end, step = 3;
        Integer allPages = all/pageSize;
        if(all%pageSize != 0)allPages++;

        if (currentPage + step <= allPages - 1) {
            end = currentPage + step;
        } else {
            end = allPages.intValue() - 1;
        }

        if (currentPage - step >= 0) {
            start = currentPage - step;
        } else {
            start = 0;
        }

        List<Integer> paginator = new ArrayList<Integer>();
        for (int i = start; i <= end; i++)
            paginator.add(i);

        return paginator;
    }

    public List<Integer> paginator(Integer all) {
        this.all = all;
        return paginator();
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

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
