package com.ryoga.bbs.infrastructure.fileStorage;

import com.ryoga.bbs.domain.model.image.ImageFile;
import com.ryoga.bbs.domain.model.image.ImageFileList;
import com.ryoga.bbs.domain.model.image.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Repository
public class S3FileStorageForImage implements ImageRepository {

    /** S3 操作SDK */
    private final S3Client s3Client;

    private final String bucketName;

    private final String logsImagePrefix;

    /** S3 操作SDK */
    public S3FileStorageForImage(
            S3Client s3Client,
            @Value("${app.s3.client.bucket-name}")
            String bucketName,
            @Value("${app.s3.client.logs-image-prefix}")
            String logsImagePrefix
    ) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
        this.logsImagePrefix = logsImagePrefix;
    }

    public void save(ImageFileList imageFileList) throws IOException {
        try {
            for (ImageFile imageFile : imageFileList.getImageFiles()) {
                s3Client.putObject(
                        PutObjectRequest.builder()
                                .bucket(bucketName)
                                .key(imageFile.getS3Key(logsImagePrefix, imageFileList.getUserId(), imageFileList.getLogId()))
                                .contentType(imageFile.getFileData().getContentType())
                                .build(),
                        RequestBody.fromBytes(imageFile.getFileData().getBytes())
                );
            }
        } catch (Exception e) {
            throw new IOException("S3への保存に失敗しました", e);
        }
    }



}
