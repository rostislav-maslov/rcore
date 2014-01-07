package com.ub.core.pages.services;

import com.ub.core.pages.models.PageDoc;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPageService extends PagingAndSortingRepository<PageDoc, String> {


}
