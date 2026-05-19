package com.ryoga.bbs.domain.model.image;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;
import com.ryoga.bbs.domain.type.Id;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ImageId {

    private final Id value;

    public ImageId(Id value) {
        if(value == null) {
            throw new DomainLogicInvalidException("イメージIDは必須です");
        }
        this.value = value;
    }

    public String value() {
        return value.value();
    }
}
