package com.ub.core.pages.services;

import com.ub.core.pages.models.TagDoc;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ITagService extends PagingAndSortingRepository<TagDoc, String> {
}
