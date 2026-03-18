package com.ryoga.bbs.infrastructure.database.command.log;

import com.ryoga.bbs.infrastructure.database.command.log.RecordEntity.LogListRecordEntity;
import com.ryoga.bbs.infrastructure.database.command.log.RecordEntity.LogRecordEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
    void save(LogRecordEntity entity);
    LogListRecordEntity findAllLog(byte[] userId);
}
