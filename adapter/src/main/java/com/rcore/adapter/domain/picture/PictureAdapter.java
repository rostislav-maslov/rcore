package com.rcore.adapter.domain.picture;

import com.rcore.domain.picture.config.PictureConfig;
import lombok.Getter;

@Getter
public class PictureAdapter {
    private final PictureConfig pictureConfig;
    private PictureAllAdapter all;
    private PictureSecuredAdapter admin;

    public PictureAdapter(PictureConfig pictureConfig) {
        this.pictureConfig = pictureConfig;
        this.all = new PictureAllAdapter(pictureConfig);
        this.admin = new PictureSecuredAdapter(pictureConfig);
    }
}
