package com.ryoga.bbs.scenario.result;

public record SignInResult(
        String accessToken,
        String refreshToken
) {}

