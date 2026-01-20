package com.ryoga.bbs.scenario.command;

import com.ryoga.bbs.controller.api.user.form.SignInForm;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInCommand {
    private String mailAddress;
    private String password;

    public static SignInCommand toCommand(SignInForm form) {
        return new SignInCommand(form.getMailAddress(), form.getPassword());
    }
}
