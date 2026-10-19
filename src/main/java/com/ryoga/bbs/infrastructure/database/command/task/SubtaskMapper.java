package com.ryoga.bbs.infrastructure.database.command.task;

import com.ryoga.bbs.infrastructure.database.command.task.RecordEntity.SubtaskRecordEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubtaskMapper {
    void save(SubtaskRecordEntity entity);
}
