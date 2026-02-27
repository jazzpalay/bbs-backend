package com.ryoga.bbs.domain.model.tag;

import com.ryoga.bbs.domain.model.user.UserId;
import lombok.Getter;

import java.util.List;

@Getter
public class TagList {

    private final UserId userId;
    private final List<Tag> tags;

    public TagList(UserId userId, List<Tag> tags){
        this.userId = userId;
        this.tags = tags;
    }
}
