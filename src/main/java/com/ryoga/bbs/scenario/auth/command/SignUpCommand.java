package com.ryoga.bbs.scenario.auth.command;

import com.ryoga.bbs.controller.api.user.form.SignUpForm;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpCommand {
    private String userName;
    private String password;
    private String mailAddress;

    public static SignUpCommand toCommand(SignUpForm form) {
        return new SignUpCommand(form.getUserName(), form.getPassword(), form.getMailAddress());
    }
}
