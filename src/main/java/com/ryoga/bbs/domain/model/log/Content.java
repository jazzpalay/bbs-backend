package com.ryoga.bbs.domain.model.log;

import com.ryoga.bbs.domain.model.image.ImageFile;
import com.ryoga.bbs.domain.model.image.ImageFileList;
import com.ryoga.bbs.domain.type.DomainLogicInvalidException;
import com.ryoga.bbs.scenario.image.command.ImageCommand;

public class Content {
    private final String content;
    public Content(String content) {
        if(content.length() > 20000){
            throw new DomainLogicInvalidException("ログ内容は20000文字以内です");
        }
        this.content = content;
    }

    public String value() {
        return content;
    }

    public Content replace(String before, String after) {
        return new Content(
                this.content.replace(before, after)
        );
    }

}
