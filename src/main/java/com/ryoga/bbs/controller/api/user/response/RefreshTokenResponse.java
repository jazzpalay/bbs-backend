package com.ryoga.bbs.controller.api.user.response;

public record RefreshTokenResponse(String jwt) {
    public static RefreshTokenResponse of(String jwt) {
        return new RefreshTokenResponse(jwt);
    }
}
