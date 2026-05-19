package com.ryoga.bbs.domain.model.image;

import com.ryoga.bbs.domain.model.log.LogId;
import com.ryoga.bbs.domain.model.user.UserId;
import com.ryoga.bbs.domain.type.DomainLogicInvalidException;
import com.ryoga.bbs.domain.type.Id;
import com.ryoga.bbs.scenario.image.command.ImageCommand;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Getter
@EqualsAndHashCode
public class ImageFile {

    private final ImageId imageId;

    private final MultipartFile fileData;

    private final String fileName;

    private final BlobUrl blobUrl;

    public ImageFile(ImageId imageId,MultipartFile fileData, BlobUrl blobUrl) {
        if(imageId == null) {
            throw new DomainLogicInvalidException("imageIdはnullにできません");
        }
        if(fileData == null) {
            throw new DomainLogicInvalidException("fileDataはnullにできません");
        }
        String originalFileName = fileData.getOriginalFilename();
        if(originalFileName == null) {
            throw new DomainLogicInvalidException("fileDataのファイル名はnullにできません");
        }
        if(!originalFileName.contains(".")){
            throw new DomainLogicInvalidException("fileDataのファイル名に拡張子が含まれていません");
        }
        if(blobUrl == null) {
            throw new DomainLogicInvalidException("blobUrlはnullにできません");
        }
        this.imageId = imageId;
        this.fileName = originalFileName;
        this.fileData = fileData;
        this.blobUrl = blobUrl;
    }

    public String getFileExtension () {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public String getS3Key(String prefix, UserId userId, LogId logId) {
        return prefix + "/" + userId.value() + "/" + logId.value() + "/" + imageId.value() + getFileExtension();
    }

    public static ImageFile from(ImageCommand command) {
        return new ImageFile(
                new ImageId(Id.from(command.getImageId())),
                command.getImage(),
                new BlobUrl(command.getBlobUrl())
        );
    }
}
