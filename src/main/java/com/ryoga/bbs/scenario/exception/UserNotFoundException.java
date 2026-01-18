package com.ryoga.bbs.scenario.exception;

public class UserNotFoundException extends AuthenticationException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
