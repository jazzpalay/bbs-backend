package com.ryoga.bbs.domain.model.task;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
public class Subtask {
    private final SubtaskId id;
    private final TaskId taskId;
    private final SubtaskName subtaskName;
    private final SubtaskDescription description;
    private final TaskStatus status;
    private final TaskPriority priority;
    private final StartedDate startedDate;
    private final DueDate dueDate;
    private final CompletedDate completedDate;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    //新規作成
    public Subtask(
            SubtaskId id,
            TaskId taskId,
            SubtaskName subtaskName,
            SubtaskDescription description,
            TaskStatus status,
            TaskPriority priority,
            StartedDate startedDate,
            DueDate dueDate,
            CompletedDate completedDate
    ) {
        this.id = id;
        this.taskId = taskId;
        this.subtaskName = subtaskName;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.startedDate = startedDate;
        this.dueDate = dueDate;
        this.completedDate = completedDate;
        this.createdAt = null;
        this.updatedAt = null;
        validateCompletedDate();
    }

    //作成日更新日取得用
    public Subtask(
            SubtaskId id,
            TaskId taskId,
            SubtaskName subtaskName,
            SubtaskDescription description,
            TaskStatus status,
            TaskPriority priority,
            StartedDate startedDate,
            DueDate dueDate,
            CompletedDate completedDate,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.taskId = taskId;
        this.subtaskName = subtaskName;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.startedDate = startedDate;
        this.dueDate = dueDate;
        this.completedDate = completedDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        validateCompletedDate();
    }

    private void validateCompletedDate() {
        if(status == TaskStatus.COMPLETED && (completedDate == null || completedDate.value() == null)) {
            throw new DomainLogicInvalidException("完了日は必須です");
        }
    }
}
