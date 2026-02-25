package com.ryoga.bbs.controller.api.user.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpForm {
    private String mailAddress;
    private String userName;
    private String password;
}
