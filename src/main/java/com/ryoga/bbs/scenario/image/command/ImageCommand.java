package com.ryoga.bbs.scenario.image.command;

import com.ryoga.bbs.controller.api.log.form.ImageMetadataForm;
import com.ryoga.bbs.domain.type.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
public class ImageCommand {

    private String imageId;
    private String blobUrl;
    private MultipartFile image;

    public static ImageCommand toCreateCommand(
            String blobUrl,
            MultipartFile image) {

        ImageCommand command = new ImageCommand();
        command.setImageId(Id.generate().value());
        command.setBlobUrl(blobUrl);
        command.setImage(image);

        return command;
    }

}
