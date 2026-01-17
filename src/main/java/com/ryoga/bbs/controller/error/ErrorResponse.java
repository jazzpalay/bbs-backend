package com.ryoga.bbs.controller.error;

public record ErrorResponse(
        String errorType,
        String message
) {}
