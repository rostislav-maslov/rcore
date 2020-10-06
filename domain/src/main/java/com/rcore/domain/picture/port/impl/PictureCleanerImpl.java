package com.rcore.domain.picture.port.impl;

import com.rcore.domain.picture.port.PictureCleaner;
import com.rcore.domain.picture.port.PictureRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PictureCleanerImpl implements PictureCleaner {

    private final PictureRepository pictureRepository;

    @Override
    public void deleteUnusedPictures() {
        pictureRepository.deleteUnused();
    }
}
