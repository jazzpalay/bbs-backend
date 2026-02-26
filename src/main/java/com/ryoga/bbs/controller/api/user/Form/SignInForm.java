package com.ryoga.bbs.controller.api.user.form;

import lombok.Data;

@Data
public class SignInForm {
    private String mailAddress;
    private String password;
}
