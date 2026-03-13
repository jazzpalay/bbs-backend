CREATE TABLE tags (
    id               BINARY(16) NOT NULL,
    user_id          BINARY(16) NOT NULL,
    name             VARCHAR(50) NOT NULL,
    color            VARCHAR(7) NOT NULL,
    created_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY(id),
    UNIQUE(user_id, name),
    FOREIGN KEY(user_id) REFERENCES user(id)
);
CREATE TABLE logs (
    id               BINARY(16) NOT NULL,
    user_id          BINARY(16) NOT NULL,
    title            VARCHAR(255) NOT NULL,
    content          TEXT NOT NULL,
    log_date         DATE NOT NULL,
    created_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES user(id)
);
CREATE TABLE log_tags(
    log_id           BINARY(16) NOT NULL,
    tag_id           BINARY(16) NOT NULL,
    PRIMARY KEY(log_id, tag_id),
    FOREIGN KEY(log_id) REFERENCES logs(id) ON DELETE CASCADE,
    FOREIGN KEY(tag_id) REFERENCES tags(id) ON DELETE CASCADE
);