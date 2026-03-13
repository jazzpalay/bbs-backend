package com.ryoga.bbs.scenario.auth.command;

import com.ryoga.bbs.controller.api.user.form.SignInForm;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInCommand {
    private String mailAddress;
    private String password;
    private String deviceId;

    public static SignInCommand toCommand(SignInForm form, String deviceId) {
        return new SignInCommand(form.getMailAddress(), form.getPassword(), deviceId);
    }
}
