package com.ryoga.bbs.controller.api.task;

import com.ryoga.bbs.controller.api.task.response.TaskListResponse;
import com.ryoga.bbs.scenario.task.queryService.TaskQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskQueryService taskQueryService;

    public TaskController(TaskQueryService taskQueryService) {
        this.taskQueryService = taskQueryService;
    }

    @GetMapping("/all")
    public ResponseEntity<TaskListResponse> getTaskList(Authentication authentication) {
        String userId = authentication.getName();
        TaskListResponse taskListResponse = taskQueryService.getTaskList(userId);
        return new ResponseEntity<>(taskListResponse, HttpStatus.OK);
    }
}
