package com.ryoga.bbs.scenario.exception;

public class DuplicateMailAddressScenarioException extends ScenarioAbortException {
    public DuplicateMailAddressScenarioException(String message) {
        super(message);
    }
}
