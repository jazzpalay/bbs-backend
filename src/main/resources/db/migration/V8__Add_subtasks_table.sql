CREATE TABLE subtasks (
    id               BINARY(16) NOT NULL,
    task_id          BINARY(16) NOT NULL,
    subtask_name     VARCHAR(255) NOT NULL,
    description      TEXT NULL,
    status           VARCHAR(20) NOT NULL,
    priority         VARCHAR(10) NOT NULL,
    started_date     DATE NOT NULL,
    due_date         DATE NULL,
    completed_date   DATE NULL,
    created_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at       TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY(task_id) REFERENCES tasks(id) ON DELETE CASCADE
);
