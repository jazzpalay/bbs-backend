package com.ryoga.bbs.domain.model.log;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;

public class Title {
    private final String title;
    public Title(String title) {
        if(title.length() > 30){
            throw new DomainLogicInvalidException("タイトルは30文字以内です");
        }
        this.title = title;
    }

    public String value() {
        return title;
    }
}
