package com.rcore.adapter.domain.file;

import com.rcore.adapter.domain.file.dto.FileDTO;
import com.rcore.adapter.domain.file.mapper.FileMapper;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.file.config.FileConfig;
import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.exception.FileNotFoundException;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.util.Optional;

@RequiredArgsConstructor
public class FileAdminAdapter {
    private FileMapper fileMapper = new FileMapper();
    private final FileConfig fileConfig;

    public FileDTO create(InputStream content, String fileName, String contentType, boolean isPrivate) throws AuthorizationException, AuthenticationException {
        return fileMapper.map(fileConfig.admin.createUseCase()
                .create(content, fileName, contentType, isPrivate));
    }

    public Boolean delete(FileDTO file) throws AuthorizationException, AuthenticationException {
        return fileConfig.admin.deleteUseCase()
                .delete(fileMapper.inverseMap(file));
    }

    public FileDTO update(FileDTO file) throws AuthorizationException, FileNotFoundException, AuthenticationException {
        return fileMapper.map(fileConfig.admin.updateUseCase()
                .update(fileMapper.inverseMap(file)));
    }

    public Optional<FileDTO> findById(String id) throws AuthorizationException, AuthenticationException {
        return fileConfig.admin.viewUseCase()
                .findById(id)
                .map(fileMapper::map);
    }
    public SearchResult<FileDTO> find(Long size, Long skip) throws AuthorizationException, AuthenticationException {
        SearchResult<FileEntity> result = fileConfig.admin
                .viewUseCase().find(size, skip);

        return SearchResult.withItemsAndCount(
                fileMapper.mapAll(result.getItems()),
                result.getCount()
        );
    }

    public Optional<InputStream> getInputStream(String id) throws AuthorizationException, FileNotFoundException, AuthenticationException {
        return fileConfig.admin.viewUseCase()
                .getInputStream(id);
    }
}
