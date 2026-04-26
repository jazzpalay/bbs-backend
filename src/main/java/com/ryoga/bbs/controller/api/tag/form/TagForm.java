package com.ryoga.bbs.controller.api.tag.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TagForm {
    @NotBlank(message = "タグ名は必須です")
    @Size(max = 10, message = "タグ名は10文字以内です")
    private String tagName;
    @NotBlank(message = "タグ色は必須です")
    private String tagColor;
}
