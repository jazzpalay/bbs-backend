package com.ryoga.bbs.domain.model.log;

import com.ryoga.bbs.domain.model.tag.LogTagIds;
import com.ryoga.bbs.domain.model.user.UserId;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository {
    void saveLog(Log log);
    void saveLogTags(LogTagIds list);
    void update(Log log);
    void deleteLog(LogId logId, UserId userId);
    void deleteLogTags(LogId logId);
    boolean existsById(LogId logId, UserId userId);
}
