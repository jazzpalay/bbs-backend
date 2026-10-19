package com.ryoga.bbs.controller.api.task.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubtaskForm {
    @NotBlank(message = "親タスクIDは必須です")
    private String taskId;
    @NotBlank(message = "サブタスク名は必須です")
    @Size(max = 255, message = "サブタスク名は255文字以内です")
    private String subtaskName;
    private String description;
    @NotBlank(message = "状態は必須です")
    private String status;
    @NotBlank(message = "優先度は必須です")
    private String priority;
    @NotBlank(message = "着手日は必須です")
    private String startedDate;
    private String dueDate;
}
