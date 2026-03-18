package com.ryoga.bbs.domain.model.log;

import com.ryoga.bbs.domain.model.tag.LogTagIds;
import com.ryoga.bbs.domain.model.user.UserId;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository {
    void save(Log log);
    void save(LogTagIds list);
}
