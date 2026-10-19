package com.ryoga.bbs.scenario.task;

import com.ryoga.bbs.domain.model.task.*;
import com.ryoga.bbs.domain.model.user.UserId;
import com.ryoga.bbs.domain.model.user.UserService;
import com.ryoga.bbs.domain.type.Id;
import com.ryoga.bbs.scenario.exception.TaskNotFoundException;
import com.ryoga.bbs.scenario.exception.UserNotFoundException;
import com.ryoga.bbs.scenario.task.command.SubtaskCommand;
import com.ryoga.bbs.scenario.task.command.TaskCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskScenario {

    private final TaskService taskService;
    private final UserService userService;

    public TaskScenario(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @Transactional
    public void createTask(TaskCommand command) {
        UserId userId = new UserId(Id.from(command.getUserId()));

        if (!userService.existsById(userId)) {
            throw new UserNotFoundException("指定のユーザーは存在しません。");
        }

        Task task = new Task(
                new TaskId(Id.from(command.getTaskId())),
                userId,
                new TaskName(command.getTaskName()),
                new TaskDescription(command.getDescription()),
                TaskStatus.fromValue(command.getStatus()),
                TaskPriority.fromValue(command.getPriority()),
                new StartedDate(command.getStartedDate()),
                new DueDate(command.getDueDate()),
                new CompletedDate(null)
        );

        taskService.saveTask(task);
    }

    @Transactional
    public void createSubtask(SubtaskCommand command) {
        UserId userId = new UserId(Id.from(command.getUserId()));
        TaskId taskId = new TaskId(Id.from(command.getTaskId()));

        if (!userService.existsById(userId)) {
            throw new UserNotFoundException("指定のユーザーは存在しません。");
        }

        if (!taskService.existsById(taskId, userId)) {
            throw new TaskNotFoundException("指定のタスクは存在しません。");
        }

        Subtask subtask = new Subtask(
                new SubtaskId(Id.from(command.getSubtaskId())),
                taskId,
                new SubtaskName(command.getSubtaskName()),
                new SubtaskDescription(command.getDescription()),
                TaskStatus.fromValue(command.getStatus()),
                TaskPriority.fromValue(command.getPriority()),
                new StartedDate(command.getStartedDate()),
                new DueDate(command.getDueDate()),
                new CompletedDate(null)
        );

        taskService.saveSubtask(subtask);
    }
}
