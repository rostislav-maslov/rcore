package com.rcore.domain.database.memory.file.port;

import com.rcore.domain.database.memory.base.port.BaseIdGeneratorImpl;
import com.rcore.domain.file.port.FileStorage;
import com.rcore.domain.user.port.IdGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FileStorageImpl implements FileStorage {

    private IdGenerator<String> idGenerator = new BaseIdGeneratorImpl();
    private Map<String, File> container = new HashMap<>();

    @Override
    public String store(File file) {
        container.put(idGenerator.generate(), file);
        return file.getPath();
    }

    @Override
    public void remove(String filePath) {
        container.values()
                .removeIf(file -> file.getPath().equals(filePath));
    }

    @Override
    public Optional<InputStream> getInputStream(String filePath) {
        Optional<File> file = container.values()
                .stream()
                .filter(f -> f.getPath().equals(filePath))
                .findFirst();

        if (!file.isPresent())
            return Optional.empty();

        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(file.get());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(inputStream);
    }
}
