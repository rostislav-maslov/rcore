package com.ub.core.file.services;

import com.ub.core.file.store.FileInfo;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface IFileService extends PagingAndSortingRepository<FileInfo, ObjectId> {
}
