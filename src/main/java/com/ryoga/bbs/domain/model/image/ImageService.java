package com.ryoga.bbs.domain.model.image;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void saveImageList(ImageFileList imageFileList) throws IOException {
        imageRepository.save(imageFileList);
    }
}
