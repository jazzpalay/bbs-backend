package com.ryoga.bbs.infrastructure.database.query.task;

import com.ryoga.bbs.controller.api.task.response.TaskListResponse;
import com.ryoga.bbs.controller.api.task.response.TaskResponse;
import com.ryoga.bbs.infrastructure.database.query.task.RecordEntity.TaskRecordEntity;
import com.ryoga.bbs.scenario.task.queryService.TaskQueryService;
import com.ryoga.bbs.util.MySQLTool;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskQueryDataSource implements TaskQueryService {

    private final TaskQueryMapper taskQueryMapper;

    public TaskQueryDataSource(TaskQueryMapper taskQueryMapper) {
        this.taskQueryMapper = taskQueryMapper;
    }

    @Override
    public TaskListResponse getTaskList(String userId) {
        List<TaskRecordEntity> entities = taskQueryMapper.findAllTask(MySQLTool.stringToBytes(userId));

        List<TaskResponse> list = entities.stream()
                .map(entity -> {
                    TaskResponse response = new TaskResponse();
                    response.setTaskId(MySQLTool.bytesToString(entity.getId()));
                    response.setTaskName(entity.getTaskName());
                    response.setDescription(entity.getDescription());
                    response.setStatus(entity.getStatus());
                    response.setPriority(entity.getPriority());
                    response.setStartedDate(entity.getStartedDate() != null ? entity.getStartedDate().toString() : null);
                    response.setDueDate(entity.getDueDate() != null ? entity.getDueDate().toString() : null);
                    response.setCompletedDate(entity.getCompletedDate() != null ? entity.getCompletedDate().toString() : null);
                    return response;
                })
                .collect(Collectors.toList());

        return new TaskListResponse(userId, list);
    }
}
