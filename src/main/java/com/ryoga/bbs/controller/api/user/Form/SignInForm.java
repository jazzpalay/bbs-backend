package com.ryoga.bbs.controller.api.user.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInForm {
    @NotBlank(message = "メールアドレスは必須です")
    private String mailAddress;
    @NotBlank(message = "パスワードは必須です")
    private String password;
}
