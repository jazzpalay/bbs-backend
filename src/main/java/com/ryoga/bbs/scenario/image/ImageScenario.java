package com.ryoga.bbs.scenario.image;

import com.ryoga.bbs.domain.model.image.ImageFileList;
import com.ryoga.bbs.domain.model.image.ImageService;
import com.ryoga.bbs.scenario.image.command.ImageCommand;
import com.ryoga.bbs.scenario.image.command.ImageCommandList;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ImageScenario {

    private final ImageService imageService;

    public ImageScenario(ImageService imageService) {
        this.imageService = imageService;
    }


    public ImageFileList uploadImage(ImageCommandList imageCommandList) throws IOException {
        ImageFileList imageFileList = ImageFileList.from(imageCommandList);
        imageService.saveImageList(imageFileList);
        return imageFileList;
    }
}
