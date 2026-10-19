package com.ryoga.bbs.infrastructure.database.command.task;

import com.ryoga.bbs.domain.model.task.Subtask;
import com.ryoga.bbs.domain.model.task.Task;
import com.ryoga.bbs.domain.model.task.TaskId;
import com.ryoga.bbs.domain.model.task.TaskRepository;
import com.ryoga.bbs.domain.model.user.UserId;
import com.ryoga.bbs.infrastructure.database.command.task.RecordEntity.SubtaskRecordEntity;
import com.ryoga.bbs.infrastructure.database.command.task.RecordEntity.TaskRecordEntity;
import com.ryoga.bbs.util.MySQLTool;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TaskDataSource implements TaskRepository {

    private final TaskMapper taskMapper;
    private final SubtaskMapper subtaskMapper;

    public TaskDataSource(TaskMapper taskMapper, SubtaskMapper subtaskMapper) {
        this.taskMapper = taskMapper;
        this.subtaskMapper = subtaskMapper;
    }

    @Override
    public void saveTask(Task task) {
        TaskRecordEntity entity = new TaskRecordEntity();
        entity.setId(MySQLTool.stringToBytes(task.getId().value()));
        entity.setUserId(MySQLTool.stringToBytes(task.getUserId().value()));
        entity.setTaskName(task.getTaskName().value());
        entity.setDescription(task.getDescription().value());
        entity.setStatus(task.getStatus().value());
        entity.setPriority(task.getPriority().value());
        entity.setStartedDate(task.getStartedDate().value());
        entity.setDueDate(task.getDueDate().value());
        entity.setCompletedDate(task.getCompletedDate().value());

        taskMapper.save(entity);
    }

    @Override
    public boolean existsById(TaskId taskId, UserId userId) {
        return Optional
                .ofNullable(taskMapper.findTask(MySQLTool.stringToBytes(taskId.value()), MySQLTool.stringToBytes(userId.value())))
                .isPresent();
    }

    @Override
    public void saveSubtask(Subtask subtask) {
        SubtaskRecordEntity entity = new SubtaskRecordEntity();
        entity.setId(MySQLTool.stringToBytes(subtask.getId().value()));
        entity.setTaskId(MySQLTool.stringToBytes(subtask.getTaskId().value()));
        entity.setSubtaskName(subtask.getSubtaskName().value());
        entity.setDescription(subtask.getDescription().value());
        entity.setStatus(subtask.getStatus().value());
        entity.setPriority(subtask.getPriority().value());
        entity.setStartedDate(subtask.getStartedDate().value());
        entity.setDueDate(subtask.getDueDate().value());
        entity.setCompletedDate(subtask.getCompletedDate().value());

        subtaskMapper.save(entity);
    }
}
