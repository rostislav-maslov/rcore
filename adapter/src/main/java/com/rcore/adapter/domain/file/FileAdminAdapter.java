package com.rcore.adapter.domain.file;

import com.rcore.adapter.domain.file.dto.FileDTO;
import com.rcore.adapter.domain.file.mapper.FileMapper;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.adapter.domain.user.mapper.UserMapper;
import com.rcore.domain.file.config.FileConfig;
import com.rcore.domain.file.exception.FileNotFoundException;
import com.rcore.domain.token.exception.AuthorizationException;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.util.Optional;

@RequiredArgsConstructor
public class FileAdminAdapter {
    private FileMapper fileMapper = new FileMapper();
    private UserMapper userMapper = new UserMapper();
    private final FileConfig fileConfig;

    public FileDTO create(UserDTO actor, InputStream content, String fileName, String contentType, boolean isPrivate) throws AuthorizationException {
        return fileMapper.map(fileConfig.admin.createUseCase(userMapper.inverseMap(actor))
                .create(content, fileName, contentType, isPrivate));
    }

    public Boolean delete(UserDTO actor, FileDTO file) throws AuthorizationException {
        return fileConfig.admin.deleteUseCase(userMapper.inverseMap(actor))
                .delete(fileMapper.inverseMap(file));
    }

    public FileDTO update(UserDTO actor, FileDTO file) throws AuthorizationException, FileNotFoundException {
        return fileMapper.map(fileConfig.admin.updateUseCase(userMapper.inverseMap(actor))
                .update(fileMapper.inverseMap(file)));
    }

    public Optional<FileDTO> findById(UserDTO actor, String id) throws AuthorizationException {
        return fileConfig.admin.viewUseCase(userMapper.inverseMap(actor))
                .findById(id)
                .map(fileMapper::map);
    }

    public Optional<InputStream> getInputStream(UserDTO actor, String id) throws AuthorizationException, FileNotFoundException {
        return fileConfig.admin.viewUseCase(userMapper.inverseMap(actor))
                .getInputStream(id);
    }
}
