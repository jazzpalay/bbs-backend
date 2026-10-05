package com.ryoga.bbs.infrastructure.database.query.task.RecordEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TaskRecordEntity {
    private byte[] id;
    private byte[] userId;
    private String taskName;
    private String description;
    private String status;
    private String priority;
    private LocalDate startedDate;
    private LocalDate dueDate;
    private LocalDate completedDate;
}
