package com.ryoga.bbs.scenario.task.command;

import com.ryoga.bbs.controller.api.task.form.TaskForm;
import com.ryoga.bbs.domain.type.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskCommand {
    private String taskId;
    private String userId;
    private String taskName;
    private String description;
    private String status;
    private String priority;
    private String startedDate;
    private String dueDate;

    public static TaskCommand toCreateCommand(TaskForm form, String userId) {
        TaskCommand command = new TaskCommand();
        command.setTaskId(Id.generate().value());
        command.setUserId(userId);
        command.setTaskName(form.getTaskName());
        command.setDescription(form.getDescription());
        command.setStatus(form.getStatus());
        command.setPriority(form.getPriority());
        command.setStartedDate(form.getStartedDate());
        command.setDueDate(form.getDueDate());

        return command;
    }
}
