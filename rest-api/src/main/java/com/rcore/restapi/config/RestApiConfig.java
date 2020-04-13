package com.rcore.restapi.config;

import com.rcore.adapter.domain.file.FileAllAdapter;
import com.rcore.adapter.domain.picture.PictureAllAdapter;
import com.rcore.domain.file.config.FileConfig;
import com.rcore.domain.file.port.FileIdGenerator;
import com.rcore.domain.file.port.FileRepository;
import com.rcore.domain.file.port.FileStorage;
import com.rcore.domain.picture.config.PictureConfig;
import com.rcore.domain.picture.port.PictureCompressor;
import com.rcore.domain.picture.port.PictureIdGenerator;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.port.PictureStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * В данном конфиге создаем бины всех адаптеров
 */
@RequiredArgsConstructor
@Configuration
public class RestApiConfig {

    private final PictureRepository pictureRepository;
    private final PictureIdGenerator pictureIdGenerator;
    private final PictureStorage pictureStorage;
    private final PictureCompressor pictureCompressor;

    private final FileRepository fileRepository;
    private final FileIdGenerator fileIdGenerator;
    private final FileStorage fileStorage;

    @Bean
    public FileAllAdapter fileAllAdapter() {
        return new FileAllAdapter(new FileConfig(fileRepository, fileIdGenerator, fileStorage));
    }

    @Bean
    public PictureAllAdapter pictureAllAdapter() {
        return new PictureAllAdapter(new PictureConfig(pictureRepository, pictureIdGenerator, pictureStorage, pictureCompressor));
    }

}
