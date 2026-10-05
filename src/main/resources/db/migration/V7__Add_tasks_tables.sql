CREATE TABLE tasks (
    id               BINARY(16) NOT NULL,
    user_id          BINARY(16) NOT NULL,
    task_name        VARCHAR(255) NOT NULL,
    description      TEXT NULL,
    status           VARCHAR(20) NOT NULL,
    priority         VARCHAR(10) NOT NULL,
    started_date     DATE NOT NULL,
    due_date         DATE NULL,
    completed_date   DATE NULL,
    created_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at       TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES user(id),
    INDEX idx_tasks_user_started_date (user_id, started_date)
);

CREATE TABLE task_logs (
    task_id          BINARY(16) NOT NULL,
    log_id           BINARY(16) NOT NULL,
    created_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY(task_id, log_id),
    FOREIGN KEY(task_id) REFERENCES tasks(id) ON DELETE CASCADE,
    FOREIGN KEY(log_id) REFERENCES logs(id) ON DELETE CASCADE,
    INDEX idx_task_logs_task (task_id),
    INDEX idx_task_logs_log (log_id)
);
