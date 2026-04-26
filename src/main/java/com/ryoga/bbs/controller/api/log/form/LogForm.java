package com.ryoga.bbs.controller.api.log.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
public class LogForm {
    @NotBlank(message = "タイトルは必須です")
    @Size(max = 30, message = "タイトルは30文字以内です")
    private String title;
    @NotBlank(message = "ログ内容は必須です")
    @Size(max = 20000, message = "ログ内容は20000文字以内です")
    private String content;
    @NotBlank(message = "作業日は必須です")
    private String logDate;
    private List<String> tagIds;
}
