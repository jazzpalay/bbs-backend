package com.ryoga.bbs.controller.api.task.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskForm {
    @NotBlank(message = "タスク名は必須です")
    @Size(max = 255, message = "タスク名は255文字以内です")
    private String taskName;
    private String description;
    @NotBlank(message = "状態は必須です")
    private String status;
    @NotBlank(message = "優先度は必須です")
    private String priority;
    @NotBlank(message = "着手日は必須です")
    private String startedDate;
    private String dueDate;
}
