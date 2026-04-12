package com.ryoga.bbs.infrastructure.database.command.log.RecordEntity;

import com.ryoga.bbs.infrastructure.database.command.tag.RecordEntity.TagRecordEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class LogRecordEntity {
    private byte[] id;
    private byte[] userId;
    private String title;
    private String content;
    private LocalDate logDate;
    private List<TagRecordEntity> tagList;
}
