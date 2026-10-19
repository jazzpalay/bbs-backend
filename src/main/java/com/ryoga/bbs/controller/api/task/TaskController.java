package com.ryoga.bbs.controller.api.task;

import com.ryoga.bbs.controller.api.task.form.SubtaskForm;
import com.ryoga.bbs.controller.api.task.form.TaskForm;
import com.ryoga.bbs.controller.api.task.response.TaskListResponse;
import com.ryoga.bbs.scenario.task.TaskScenario;
import com.ryoga.bbs.scenario.task.command.SubtaskCommand;
import com.ryoga.bbs.scenario.task.command.TaskCommand;
import com.ryoga.bbs.scenario.task.queryService.TaskQueryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskQueryService taskQueryService;
    private final TaskScenario taskScenario;

    public TaskController(TaskQueryService taskQueryService, TaskScenario taskScenario) {
        this.taskQueryService = taskQueryService;
        this.taskScenario = taskScenario;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTask(Authentication authentication, @RequestBody @Valid TaskForm taskForm) {
        String userId = authentication.getName();
        TaskCommand taskCommand = TaskCommand.toCreateCommand(taskForm, userId);
        taskScenario.createTask(taskCommand);
        return new ResponseEntity<>(taskCommand.getTaskId(), HttpStatus.CREATED);
    }

    @PostMapping("sub/create")
    public ResponseEntity<String> createSubTask(Authentication authentication, @RequestBody @Valid SubtaskForm subtaskForm) {
        String userId = authentication.getName();
        SubtaskCommand subtaskCommand = SubtaskCommand.toCreateCommand(subtaskForm, userId);
        taskScenario.createSubtask(subtaskCommand);
        return new ResponseEntity<>(subtaskCommand.getSubtaskId(), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<TaskListResponse> getTaskList(Authentication authentication) {
        String userId = authentication.getName();
        TaskListResponse taskListResponse = taskQueryService.getTaskList(userId);
        return new ResponseEntity<>(taskListResponse, HttpStatus.OK);
    }
}
