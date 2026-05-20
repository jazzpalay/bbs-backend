package com.ryoga.bbs.domain.model.log;

import com.ryoga.bbs.domain.model.image.ImageFile;
import com.ryoga.bbs.domain.model.image.ImageFileList;
import com.ryoga.bbs.domain.model.tag.LogTagIds;
import com.ryoga.bbs.domain.model.user.UserId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final LogRepository logRepository;

    private final String cloudfrontUrl;

    private final String logsImagePrefix;

    public  LogService(LogRepository logRepository,
                       @Value("${app.s3.client.cloudfront-url}")
                       String cloudfrontUrl,
                       @Value("${app.s3.client.logs-image-prefix}")
                       String logsImagePrefix
    ) {
        this.logRepository = logRepository;
        this.cloudfrontUrl = cloudfrontUrl;
        this.logsImagePrefix = logsImagePrefix;
    }

    public void saveLog(Log log, LogTagIds tagIds){
        logRepository.saveLog(log);
        if(!tagIds.isTagEmpty()){
            logRepository.saveLogTags(tagIds);
        }
    }

    public void updateLog(Log log, LogTagIds tagIds) {
        logRepository.update(log);

        logRepository.deleteLogTags(log.getId());
        if(!tagIds.isTagEmpty()){
            logRepository.saveLogTags(tagIds);
        }
    }

    public Content replaceBlobUrlToS3Url(Content content, ImageFileList imageFileList) {
        if(imageFileList == null || imageFileList.isEmpty()){
            return content;
        }

        Content replacedContent = content;
        String baseUrl = cloudfrontUrl + logsImagePrefix;

        //BlobURLをS3用URLに置換
        for (ImageFile imageFile : imageFileList.getImageFiles()) {
            replacedContent = replacedContent
                    .replace(imageFile.getBlobUrl().getValue(), imageFile.getS3Key(baseUrl, imageFileList.getUserId(), imageFileList.getLogId()));
        }
        return replacedContent;
    }

    public void deleteLog(LogId logId, UserId userId) {
        logRepository.deleteLog(logId, userId);
    }

    public boolean existsById(LogId logId, UserId userId) {
        return logRepository.existsById(logId, userId);
    }
}
