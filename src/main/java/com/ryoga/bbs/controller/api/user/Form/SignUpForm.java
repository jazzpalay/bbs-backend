package com.ryoga.bbs.controller.api.user.Form;

import lombok.Data;

@Data
public class SignUpForm {

    private String userName;
    private String password;
    private String mailAddress;
}
