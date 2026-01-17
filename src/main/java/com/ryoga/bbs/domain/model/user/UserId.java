package com.ryoga.bbs.domain.model.user;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;
import com.ryoga.bbs.domain.type.Id;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class UserId {

    private final Id value;

    public UserId(Id id) {
        if(id == null) {
            throw new DomainLogicInvalidException("ユーザーIDは必須です");
        }
        this.value = id;
    }

    public String value() {
        return value.value();
    }

}
