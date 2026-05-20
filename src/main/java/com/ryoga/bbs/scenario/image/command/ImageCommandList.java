package com.ryoga.bbs.scenario.image.command;

import com.ryoga.bbs.controller.api.log.form.ImageMetadataForm;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ImageCommandList {

    private String userId;
    private String logId;
    private List<ImageCommand> imageList;

    public static ImageCommandList toCreateCommand(
                            String userId,
                            String logId,
                            List<ImageMetadataForm> metadataFormList,
                            List<MultipartFile> imageList)
    {
        if(metadataFormList == null || imageList == null || metadataFormList.size() != imageList.size()) {
            return null;
        }
        ImageCommandList commandList = new ImageCommandList();
        commandList.setUserId(userId);
        commandList.setLogId(logId);

        List<ImageCommand> imageCommandList = new ArrayList<>();
        for (int i = 0; i < metadataFormList.size(); i++) {
            ImageCommand imageCommand = ImageCommand.toCreateCommand(metadataFormList.get(i).getBlobUrl(), imageList.get(i));
            imageCommandList.add(imageCommand);
        }
        commandList.setImageList(imageCommandList);

        return commandList;
    }

    public boolean isEmpty() {
        return imageList == null || imageList.isEmpty();
    }

}
