package com.ryoga.bbs.domain.model.user;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class MailAddress {
    private final String mailAddress;

    public MailAddress(String value){
        if(value == null || value.isEmpty()){
            throw new DomainLogicInvalidException("メールアドレスは必須です");
        }

        if(value.length() > 50) {
            throw new DomainLogicInvalidException("ユーザー名は50文字以内で入力してください");
        }

        this.mailAddress = value;
    }

    public String value(){
        return this.mailAddress;
    }
}
