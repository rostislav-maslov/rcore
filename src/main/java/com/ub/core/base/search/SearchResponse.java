package com.ub.core.base.search;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse {
    protected Integer currentPage = 0;
    protected Integer pageSize = 10;
    protected Long all = 0l;
    protected String query = "";

    public Long prevNum(Long all) {
        this.all = all;
        return prevNum();
    }

    public Long prevNum() {
        if (currentPage - 1 >= 0)
            return currentPage - 1l;
        else
            return 0l;
    }

    public Long nextNum(Long all) {
        this.all = all;
        return nextNum();
    }

    public Long nextNum() {
        Long allPages = all / pageSize;
        if (allPages == 0) {
            return 0l;
        }

        if (all % pageSize != 0) allPages++;

        if (currentPage + 1 <= allPages - 1)
            return currentPage + 1l;
        else
            return allPages - 1;
    }

    public Integer lastPage() {
        Long allPages = all / pageSize;
        allPages -= 1;
        if (all % pageSize != 0) allPages++;
        if(allPages < 0l){
            allPages = 0l;
        }
        return allPages.intValue();
    }

    public List<Long> paginator(Integer step) {
        Integer begin, end, beginDif = null, endDif = null;
        Integer allPages = lastPage();

        begin = currentPage - step;
        if (begin < 0) {
            beginDif = begin;
            begin = 0;
        }

        end = currentPage + step;
        if (end > allPages) {
            endDif = end - allPages;
            end = allPages;
        }

        if (beginDif != null) {
            end -= beginDif;
            if (end > allPages) {
                end = allPages;
            }
        }
        if (endDif != null) {
            begin -= endDif;
            if (begin < 0) {
                begin = 0;
            }
        }

        List<Long> result = new ArrayList<Long>();
        for (Integer i = begin; i <= end; i++) {
            result.add(i.longValue());
        }

        return result;
    }

    public List<Long> paginator() {
        return this.paginator(3);
//        Integer start, end, step = 3;
//        Long allPages = all / pageSize;
//        if (all % pageSize != 0) allPages++;
//
//        if (currentPage + step <= allPages - 1) {
//            end = currentPage + step;
//        } else {
//            end = allPages.intValue() - 1;
//        }
//
//        if (currentPage - step >= 0) {
//            start = currentPage - step;
//        } else {
//            start = 0;
//        }
//
//        List<Long> paginator = new ArrayList<Long>();
//        for (long i = start; i <= end; i++)
//            paginator.add(i);
//
//        return paginator;
    }

    public List<Long> paginator(Long all) {
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

    public Long getAll() {
        return all;
    }

    public void setAll(Long all) {
        this.all = all;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
