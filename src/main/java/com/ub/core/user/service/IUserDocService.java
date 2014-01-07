package com.ub.core.user.service;

import com.ub.core.user.models.UserDoc;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IUserDocService extends PagingAndSortingRepository<UserDoc, ObjectId> {
}
