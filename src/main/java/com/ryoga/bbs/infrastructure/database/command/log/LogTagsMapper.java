package com.ryoga.bbs.infrastructure.database.command.log;

import com.ryoga.bbs.infrastructure.database.command.log.RecordEntity.LogTagsRecordEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogTagsMapper {
    void save(LogTagsRecordEntity entity);
    void delete(byte[] logId);

}
