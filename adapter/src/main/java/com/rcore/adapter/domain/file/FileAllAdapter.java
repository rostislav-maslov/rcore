package com.rcore.adapter.domain.file;

import com.rcore.adapter.domain.file.dto.FileDTO;
import com.rcore.adapter.domain.file.mapper.FileMapper;
import com.rcore.domain.file.config.FileConfig;
import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.exception.FileAccessException;
import com.rcore.domain.file.exception.FileNotFoundException;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.util.Optional;

@RequiredArgsConstructor
public class FileAllAdapter {

    private FileMapper fileMapper = new FileMapper();
    private final FileConfig fileConfig;

    public Optional<FileDTO> findById(String id) throws FileAccessException {
        return fileConfig.all.viewUseCase().findById(id)
                .map(fileMapper::map);
    }

    public Optional<InputStream> getInputStream(String id) throws FileNotFoundException, FileAccessException {
        return fileConfig.all.viewUseCase().getInputStream(id);
    }

    public FileDTO create(InputStream content, String fileName, String contentType) {
        return fileMapper.map(fileConfig.all.createUseCase().create(content, fileName, contentType));
    }

    public Boolean delete(FileEntity fileEntity) throws FileAccessException {
        return fileConfig.all.deleteUseCase().delete(fileEntity);
    }

    public Boolean deleteById(String id) throws FileNotFoundException, FileAccessException {
        return fileConfig.all.deleteUseCase().deleteById(id);
    }
}
