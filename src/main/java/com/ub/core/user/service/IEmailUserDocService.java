package com.ub.core.user.service;

import com.ub.core.user.models.EmailUserDoc;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IEmailUserDocService extends PagingAndSortingRepository<EmailUserDoc, ObjectId> {
    List<EmailUserDoc> findByEmail(String email);
}
