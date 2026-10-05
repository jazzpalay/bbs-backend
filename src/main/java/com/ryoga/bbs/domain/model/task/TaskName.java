package com.ryoga.bbs.domain.model.task;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;

public class TaskName {

    private final String value;

    public TaskName(String value) {
        if(value == null || value.isEmpty()) {
            throw new DomainLogicInvalidException("タスク名は必須です");
        }
        if(value.length() > 255) {
            throw new DomainLogicInvalidException("タスク名は255文字以内で入力してください");
        }
        this.value = value;
    }

    public String value() {
        return value;
    }
}
