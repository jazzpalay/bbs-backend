package com.ryoga.bbs.scenario.auth.result;

public record SignInResult(
        String accessToken,
        String refreshToken
) {}

