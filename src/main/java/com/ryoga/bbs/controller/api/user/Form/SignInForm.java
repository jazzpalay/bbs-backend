package com.ryoga.bbs.controller.api.user.Form;

import lombok.Data;

@Data
public class SignInForm {
    private String mailAddress;
    private String password;
}
