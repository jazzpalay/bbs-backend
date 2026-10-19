package com.ryoga.bbs.domain.model.task;

import com.ryoga.bbs.domain.model.user.UserId;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository {
    void saveTask(Task task);
    boolean existsById(TaskId taskId, UserId userId);
    void saveSubtask(Subtask subtask);
}
