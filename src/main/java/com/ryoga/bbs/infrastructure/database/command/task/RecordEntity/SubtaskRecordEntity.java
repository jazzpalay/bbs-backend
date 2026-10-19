package com.ryoga.bbs.infrastructure.database.command.task.RecordEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SubtaskRecordEntity {
    private byte[] id;
    private byte[] taskId;
    private String subtaskName;
    private String description;
    private String status;
    private String priority;
    private LocalDate startedDate;
    private LocalDate dueDate;
    private LocalDate completedDate;
}
