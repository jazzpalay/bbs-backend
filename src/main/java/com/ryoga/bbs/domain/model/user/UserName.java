package com.ryoga.bbs.domain.model.user;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;

public class UserName {

    private String value;

    public UserName(String value) {
        if(value == null || value.isEmpty()) {
            throw new DomainLogicInvalidException("ユーザー名は必須です");
        }
        if(value.length() > 20) {
            throw new DomainLogicInvalidException("ユーザー名は20文字以内で入力してください");
        }
        this.value = value;
    }

    public String value() {
        return value;
    }
}
