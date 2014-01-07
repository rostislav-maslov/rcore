package com.ub.core.user.service;

import com.ub.core.user.models.EmailUserDoc;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IEmailUserDocService extends PagingAndSortingRepository<EmailUserDoc, String> {
}
