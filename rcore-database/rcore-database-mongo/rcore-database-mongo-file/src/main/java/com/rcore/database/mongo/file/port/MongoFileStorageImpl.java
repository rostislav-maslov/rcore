package com.rcore.database.mongo.file.port;

import com.rcore.domain.commons.port.FileStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MongoFileStorageImpl  implements FileStorage {
    private final GridFsRepository gridFsRepository;


    @Override
    public String store(InputStream content, String fileName, String contentType) {
        return gridFsRepository.save(content, fileName, contentType).getObjectId().toString();
    }

    @Override
    public void remove(String filePath) {
        gridFsRepository.delete(filePath);
    }

    @Override
    public Optional<InputStream> getFileStreamByPath(String filePath) {
        try {
            return Optional.ofNullable(gridFsRepository.getResource(gridFsRepository.findById(filePath)).getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
