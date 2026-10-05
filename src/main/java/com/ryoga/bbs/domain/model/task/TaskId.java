package com.ryoga.bbs.domain.model.task;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;
import com.ryoga.bbs.domain.type.Id;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class TaskId {
    private final Id value;

    public TaskId(Id id){
        if(id == null){
            throw new DomainLogicInvalidException("タスクIDは必須です");
        }
        this.value = id;
    }

    public String value() {
        return value.value();
    }
}
