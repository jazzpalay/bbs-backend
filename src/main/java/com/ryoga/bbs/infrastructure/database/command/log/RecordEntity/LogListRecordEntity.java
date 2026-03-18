package com.ryoga.bbs.infrastructure.database.command.log.RecordEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LogListRecordEntity {
    private byte[] userId;
    private List<LogRecordEntity> list;
}
