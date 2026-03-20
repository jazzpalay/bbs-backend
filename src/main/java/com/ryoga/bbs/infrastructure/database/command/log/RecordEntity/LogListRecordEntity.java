package com.ryoga.bbs.infrastructure.database.command.log.RecordEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LogListRecordEntity {
    private byte[] userId;
    private List<LogRecordEntity> list;
}
