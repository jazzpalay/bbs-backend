package com.ryoga.bbs.infrastructure.database.command.task;

import com.ryoga.bbs.infrastructure.database.command.task.RecordEntity.TaskRecordEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper {
    void save(TaskRecordEntity entity);
    TaskRecordEntity findTask(byte[] id, byte[] userId);
}
