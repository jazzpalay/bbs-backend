package com.ryoga.bbs.scenario.task.command;

import com.ryoga.bbs.controller.api.task.form.SubtaskForm;
import com.ryoga.bbs.domain.type.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubtaskCommand {
    private String subtaskId;
    private String taskId;
    private String userId;
    private String subtaskName;
    private String description;
    private String status;
    private String priority;
    private String startedDate;
    private String dueDate;

    public static SubtaskCommand toCreateCommand(SubtaskForm form, String userId) {
        SubtaskCommand command = new SubtaskCommand();
        command.setSubtaskId(Id.generate().value());
        command.setTaskId(form.getTaskId());
        command.setUserId(userId);
        command.setSubtaskName(form.getSubtaskName());
        command.setDescription(form.getDescription());
        command.setStatus(form.getStatus());
        command.setPriority(form.getPriority());
        command.setStartedDate(form.getStartedDate());
        command.setDueDate(form.getDueDate());

        return command;
    }
}
