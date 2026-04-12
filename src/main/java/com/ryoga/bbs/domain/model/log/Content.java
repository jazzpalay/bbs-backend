package com.ryoga.bbs.domain.model.log;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;

public class Content {
    private final String content;
    public Content(String content) {
        if(content.length() > 200){
            throw new DomainLogicInvalidException("ログ内容は200文字以内です");
        }
        this.content = content;
    }

    public String value() {
        return content;
    }
}
