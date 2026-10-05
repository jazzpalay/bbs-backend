package com.ryoga.bbs.domain.model.task;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;

import java.util.Arrays;

public enum TaskStatus {
    NOT_STARTED("未着手"),
    IN_PROGRESS("進行中"),
    COMPLETED("完了");

    private final String label;

    TaskStatus(String label) {
        this.label = label;
    }

    public String value() {
        return name();
    }

    public String label() {
        return label;
    }

    public static TaskStatus fromValue(String value) {
        return Arrays.stream(values())
                .filter(status -> status.name().equals(value))
                .findFirst()
                .orElseThrow(() -> new DomainLogicInvalidException("ステータスの値が不正です"));
    }
}
