package com.ub.core.picture.services;

import com.ub.core.picture.models.PictureColorDoc;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPictureColorService extends PagingAndSortingRepository<PictureColorDoc, ObjectId> {
}
