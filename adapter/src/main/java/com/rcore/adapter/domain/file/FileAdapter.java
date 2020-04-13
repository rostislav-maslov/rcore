package com.rcore.adapter.domain.file;

import com.rcore.domain.file.config.FileConfig;
import lombok.Getter;

@Getter
public class FileAdapter {
    private final FileConfig fileConfig;
    private FileAllAdapter all;
    private FileAdminAdapter admin;

    public FileAdapter(FileConfig fileConfig) {
        this.fileConfig = fileConfig;
        all = new FileAllAdapter(fileConfig);
        admin = new FileAdminAdapter(fileConfig);
    }
}
