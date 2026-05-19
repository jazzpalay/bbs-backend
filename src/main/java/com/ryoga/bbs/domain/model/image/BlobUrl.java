package com.ryoga.bbs.domain.model.image;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class BlobUrl {

    private final String value;

    public BlobUrl(String value) {
        if (value == null || value.isBlank()) {
            throw new DomainLogicInvalidException(
                    "blobUrlは必須です"
            );
        }

        if (!value.matches("^blob:.+")) {
            throw new DomainLogicInvalidException(
                    "blobUrlの形式が不正です"
            );
        }
        this.value = value;
    }

}
