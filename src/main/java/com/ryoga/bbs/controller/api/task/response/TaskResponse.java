package com.ryoga.bbs.controller.api.task.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskResponse {
    private String taskId;
    private String taskName;
    private String description;
    private String status;
    private String priority;
    private String startedDate;
    private String dueDate;
    private String completedDate;
}
