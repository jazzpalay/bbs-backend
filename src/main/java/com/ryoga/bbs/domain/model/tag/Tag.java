package com.ryoga.bbs.domain.model.tag;

import com.ryoga.bbs.domain.model.user.UserId;
import com.ryoga.bbs.domain.type.DomainLogicInvalidException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Tag {

    private final TagId id;
    private final UserId userId;
    private final TagName tagName;
    private final TagColor tagColor;

    public Tag(TagId id, UserId userId, TagName tagName, TagColor tagColor){
        if(id == null){
            throw new DomainLogicInvalidException("タグidは必須です");
        }
        if(userId == null){
            throw new DomainLogicInvalidException("ユーザーidは必須です");
        }
        if(tagName == null){
            throw new DomainLogicInvalidException("タグ名は必須です");
        }
        if(tagColor == null){
            throw new DomainLogicInvalidException("タグカラーは必須です");
        }

        this.id = id;
        this.userId = userId;
        this.tagName = tagName;
        this.tagColor = tagColor;
    }
}
