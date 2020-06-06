package com.rcore.domain.database.memory.file.port;

import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.port.FileRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FileRepositoryImpl implements FileRepository {
    private Map<String, FileEntity> container = new HashMap<>();

    @Override
    public Optional<FileEntity> findByPath(String path) {
        return container.values()
                .stream()
                .filter(fileEntity -> fileEntity.getFilePath().equals(path))
                .findFirst();
    }

    @Override
    public FileEntity save(FileEntity object) {
        container.put(object.getId(), object);
        return object;
    }

    @Override
    public Boolean delete(FileEntity object) {
        container.remove(object.getId());
        return true;
    }

    @Override
    public Boolean deleteById(String id) {
        container.remove(id);
        return true;
    }

    @Override
    public Optional<FileEntity> findById(String id) {
        return Optional.ofNullable(container.get(id));
    }

    @Override
    public SearchResult<FileEntity> find(SearchRequest request) {
        return null;
    }

    @Override
    public Long count() {
        return (long) container.size();
    }
}
