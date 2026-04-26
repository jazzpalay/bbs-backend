package com.ryoga.bbs.controller.api.user.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpForm {
    @NotBlank(message = "メールアドレスは必須です")
    private String mailAddress;
    @NotBlank(message = "ユーザー名は必須です")
    @Size(max = 20, message = "ユーザー名は20文字以内です")
    private String userName;
    @NotBlank(message = "パスワードは必須です。")
    private String password;
}
