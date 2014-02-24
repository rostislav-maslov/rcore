package com.ub.core.user.service;

import com.ub.core.user.models.RoleDoc;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IRoleDocService extends PagingAndSortingRepository<RoleDoc, ObjectId> {
    public List<RoleDoc> findByRoleTitle(String s);
}
