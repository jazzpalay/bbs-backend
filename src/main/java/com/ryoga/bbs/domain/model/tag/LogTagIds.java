package com.ryoga.bbs.domain.model.tag;

import com.ryoga.bbs.domain.model.log.LogId;
import lombok.Getter;

import java.util.List;

@Getter
public class LogTagIds {
    private final LogId logId;
    private final List<TagId> tags;

    public LogTagIds(LogId logId, List<TagId> tags){
        this.logId = logId;
        this.tags = tags;
    }

    public boolean isTagEmpty(){
        return tags.isEmpty();
    }
}
