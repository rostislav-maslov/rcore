package com.rcore.domain.file.port;

import com.rcore.domain.base.port.CRUDRepository;
import com.rcore.domain.file.entity.FileEntity;

import java.util.Optional;

public interface FileRepository extends CRUDRepository<String, FileEntity> {
    Optional<FileEntity> findByPath(String path);
}
