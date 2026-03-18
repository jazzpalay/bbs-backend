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
        logRepository.save(log);
        if(!tagIds.isTagEmpty()){
            logRepository.save(tagIds);
        }
    }
}
