package com.ryoga.bbs.controller.api.user.response;

public record SignInResponse(String jwt) {
    public static SignInResponse of(String jwt) {
        return new SignInResponse(jwt);
    }
}
