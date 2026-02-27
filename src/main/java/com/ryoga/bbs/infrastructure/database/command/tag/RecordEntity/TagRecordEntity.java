package com.ryoga.bbs.infrastructure.database.command.tag.RecordEntity;

import com.ryoga.bbs.domain.model.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
    public class TagRecordEntity {
    private byte[] id;
    private byte[] userId;
    private String tagName;
    private String tagColor;
}
