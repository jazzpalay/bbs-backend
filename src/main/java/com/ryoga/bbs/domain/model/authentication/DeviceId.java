package com.ryoga.bbs.domain.model.authentication;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;
import com.ryoga.bbs.domain.type.Id;

public class DeviceId {

    private final Id value;

    public DeviceId(Id id) {
        if(id == null) {
            throw new DomainLogicInvalidException("デバイスIDは必須です");
        }
        this.value = id;
    }

    public String value() {
        return value.value();
    }
}
