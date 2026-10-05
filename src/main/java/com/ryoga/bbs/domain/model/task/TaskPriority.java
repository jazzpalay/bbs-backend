package com.ryoga.bbs.domain.model.task;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;

import java.util.Arrays;

public enum TaskPriority {
    LOW("低"),
    MEDIUM("中"),
    HIGH("高");

    private final String label;

    TaskPriority(String label) {
        this.label = label;
    }

    public String value() {
        return name();
    }

    public String label() {
        return label;
    }

    public static TaskPriority fromValue(String value) {
        return Arrays.stream(values())
                .filter(priority -> priority.name().equals(value))
                .findFirst()
                .orElseThrow(() -> new DomainLogicInvalidException("優先度の値が不正です"));
    }
}
