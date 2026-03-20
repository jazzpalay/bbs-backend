package com.ryoga.bbs.domain.model.log;

import com.ryoga.bbs.domain.model.tag.LogTagIds;
import com.ryoga.bbs.domain.model.user.UserId;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final LogRepository logRepository;

    public  LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
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

    public void deleteLog(LogId logId, UserId userId) {
        logRepository.deleteLog(logId, userId);
    }

    public boolean existsById(LogId logId, UserId userId) {
        return logRepository.existsById(logId, userId);
    }
}
