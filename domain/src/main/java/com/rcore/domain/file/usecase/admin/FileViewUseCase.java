package com.rcore.domain.file.usecase.admin;

import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.exception.FileNotFoundException;
import com.rcore.domain.file.port.FileRepository;
import com.rcore.domain.file.port.FileStorage;
import com.rcore.domain.file.access.AdminFileViewAccess;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.base.port.SearchResult;

import java.io.InputStream;
import java.util.Optional;

public class FileViewUseCase extends FileAdminBaseUseCase {

    private final FileStorage fileStorage;

    public FileViewUseCase(FileRepository fileRepository, FileStorage fileStorage, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(fileRepository, new AdminFileViewAccess(), authorizationByTokenUseCase);
        this.fileStorage = fileStorage;
    }

    public Optional<FileEntity> findById(String id) throws AuthenticationException, AuthorizationException {
        checkAccess();
        return fileRepository.findById(id);
    }

    public Optional<FileEntity> search(String id) throws AuthenticationException, AuthorizationException {
        checkAccess();
        return fileRepository.findById(id);
    }

    public SearchResult<FileEntity> find(SearchRequest request) throws AuthenticationException, AuthorizationException {
        checkAccess();
        return fileRepository.find(request);
    }

    public Optional<InputStream> getInputStream(String id) throws FileNotFoundException, AuthenticationException, AuthorizationException {
        checkAccess();

        FileEntity fileEntity = findById(id)
                .orElseThrow(() -> new FileNotFoundException());

        return fileStorage.getInputStream(fileEntity.getFilePath());
    }

}