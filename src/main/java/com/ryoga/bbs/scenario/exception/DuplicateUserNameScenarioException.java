package com.ryoga.bbs.scenario.exception;

public class DuplicateUserNameScenarioException extends ScenarioAbortException {
    public DuplicateUserNameScenarioException(String message) {
        super(message);
    }
}
