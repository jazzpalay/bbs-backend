package com.ryoga.bbs.domain.type;

import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode
public class Id {
    private UUID value;

    public Id(UUID value) {

        if(value == null) {
            throw  new DomainLogicInvalidException("IDは必須です");
        }
        this.value = value;
    }

    public String value() {
        return value.toString();
    }

    public static Id from(String id){
        return new Id(UUID.fromString(id));
    }

    public static Id generate(){
        return new Id(UUID.randomUUID());
    }


}
