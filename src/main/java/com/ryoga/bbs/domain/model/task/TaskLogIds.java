package com.ryoga.bbs.domain.model.task;

import com.ryoga.bbs.domain.model.log.LogId;
import lombok.Getter;

import java.util.List;

@Getter
public class TaskLogIds {
    private final TaskId taskId;
    private final List<LogId> logIds;

    public TaskLogIds(TaskId taskId, List<LogId> logIds){
        this.taskId = taskId;
        this.logIds = logIds;
    }

    public boolean isLogEmpty(){
        return logIds.isEmpty();
    }
}
