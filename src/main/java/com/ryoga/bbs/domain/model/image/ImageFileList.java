package com.ryoga.bbs.domain.model.image;

import com.ryoga.bbs.domain.model.log.LogId;
import com.ryoga.bbs.domain.model.user.UserId;
import com.ryoga.bbs.domain.type.Id;
import com.ryoga.bbs.scenario.image.command.ImageCommandList;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode
public class ImageFileList {

    private final UserId userId;
    private final LogId logId;
    private final List<ImageFile> imageFiles;

    public ImageFileList(UserId userId, LogId logId, List<ImageFile> imageFiles) {
        this.userId = userId;
        this.logId = logId;
        this.imageFiles = imageFiles;
    }

    public static ImageFileList from(ImageCommandList commandList) {
        return new ImageFileList(
                new UserId(Id.from(commandList.getUserId())),
                new LogId(Id.from(commandList.getLogId())),
                commandList.getImageList().stream().map(ImageFile::from).toList()
        );
    }

    public boolean isEmpty() {
        return imageFiles == null || imageFiles.isEmpty();
    }
}
