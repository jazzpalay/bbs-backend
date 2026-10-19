package com.ryoga.bbs.domain.model.task;

import com.ryoga.bbs.domain.model.user.UserId;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void saveTask(Task task) {
        taskRepository.saveTask(task);
    }

    public boolean existsById(TaskId taskId, UserId userId) {
        return taskRepository.existsById(taskId, userId);
    }

    public void saveSubtask(Subtask subtask) {
        taskRepository.saveSubtask(subtask);
    }
}
