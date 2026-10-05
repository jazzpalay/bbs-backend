package com.ryoga.bbs.infrastructure.database.query.task;

import com.ryoga.bbs.infrastructure.database.query.task.RecordEntity.TaskRecordEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskQueryMapper {
    List<TaskRecordEntity> findAllTask(byte[] userId);
}
