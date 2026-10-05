package com.ryoga.bbs.domain.model.task;

import com.ryoga.bbs.domain.model.user.UserId;
import com.ryoga.bbs.domain.type.DomainLogicInvalidException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
public class Task {
    private final TaskId id;
    private final UserId userId;
    private final TaskName taskName;
    private final TaskDescription description;
    private final TaskStatus status;
    private final TaskPriority priority;
    private final StartedDate startedDate;
    private final DueDate dueDate;
    private final CompletedDate completedDate;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    //新規作成
    public Task(
            TaskId id,
            UserId userId,
            TaskName taskName,
            TaskDescription description,
            TaskStatus status,
            TaskPriority priority,
            StartedDate startedDate,
            DueDate dueDate,
            CompletedDate completedDate
    ) {
        this.id = id;
        this.userId = userId;
        this.taskName = taskName;
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
    public Task(
            TaskId id,
            UserId userId,
            TaskName taskName,
            TaskDescription description,
            TaskStatus status,
            TaskPriority priority,
            StartedDate startedDate,
            DueDate dueDate,
            CompletedDate completedDate,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.taskName = taskName;
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
