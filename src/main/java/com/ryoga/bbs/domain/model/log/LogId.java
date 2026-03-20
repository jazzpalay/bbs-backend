package com.ryoga.bbs.domain.model.log;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;
import com.ryoga.bbs.domain.type.Id;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class LogId {
    private final Id value;

    public LogId(Id id){
        if(id == null){
            throw new DomainLogicInvalidException("ログIDは必須です");
        }
        this.value = id;
    }

    public String value() {
        return value.value();
    }
}
