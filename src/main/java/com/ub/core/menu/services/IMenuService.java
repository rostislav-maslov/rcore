package com.ub.core.menu.services;

import com.ub.core.menu.models.MenuDoc;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IMenuService extends PagingAndSortingRepository<MenuDoc, ObjectId> {

}
