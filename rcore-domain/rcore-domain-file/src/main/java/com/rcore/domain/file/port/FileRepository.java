package com.rcore.domain.file.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.file.entity.FileEntity;

import java.util.Optional;

public interface FileRepository extends CRUDRepository<String, FileEntity, SearchFilters> {
    Optional<FileEntity> findByPath(String path);
}
