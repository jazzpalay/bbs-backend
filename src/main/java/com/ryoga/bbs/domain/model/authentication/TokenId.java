package com.ryoga.bbs.domain.model.authentication;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;
import com.ryoga.bbs.domain.type.Id;

public class TokenId {

    private final Id value;

    public TokenId(Id id) {
        if(id == null) {
            throw new DomainLogicInvalidException("トークンIDは必須です");
        }
        this.value = id;
    }

    public String value() {
        return value.value();
    }
}
