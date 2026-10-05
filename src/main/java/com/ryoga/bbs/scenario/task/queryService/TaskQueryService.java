package com.ryoga.bbs.scenario.task.queryService;

import com.ryoga.bbs.controller.api.task.response.TaskListResponse;

public interface TaskQueryService {
    TaskListResponse getTaskList(String userId);
}
