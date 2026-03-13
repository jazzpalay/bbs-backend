package com.ryoga.bbs.domain.model.tag;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;

public class TagName {

    private final String value;

    public TagName(String value) {
        if(value == null || value.isEmpty()) {
            throw new DomainLogicInvalidException("タグ名は必須です");
        }
        if(value.length() > 10) {
            throw new DomainLogicInvalidException("タグ名は10文字以内で入力してください");
        }
        this.value = value;
    }

    public String value() {
        return value;
    }
}
