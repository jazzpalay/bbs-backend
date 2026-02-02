package com.ryoga.bbs.controller.api.user.form;

import lombok.Data;

@Data
public class SignUpForm {

    private String userName;
    private String password;
    private String mailAddress;
}
