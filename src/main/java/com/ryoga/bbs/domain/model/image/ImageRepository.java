package com.ryoga.bbs.domain.model.image;

import org.springframework.stereotype.Repository;

import java.awt.*;
import java.io.IOException;

@Repository
public interface ImageRepository {

    void save(ImageFileList imageFileList) throws IOException;
}
