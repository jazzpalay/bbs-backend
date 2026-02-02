CREATE TABLE post (
    id                  BINARY(16) NOT NULL,
    user_id             BINARY(16) NOT NULL,
    title               VARCHAR(255) NOT NULL,
    content             TEXT       NOT NULL,
    deleted             BOOLEAN NOT NULL DEFAULT 0,
    created_at          TIMESTAMP  NOT NULL    DEFAULT     CURRENT_TIMESTAMP(),
    updated_at          TIMESTAMP  NOT NULL    DEFAULT     CURRENT_TIMESTAMP()      ON UPDATE CURRENT_TIMESTAMP(),
    PRIMARY KEY(id)
);

CREATE TABLE comment (
    id                  BINARY(16) NOT NULL,
    post_id             BINARY(16) NOT NULL,
    user_id             BINARY(16) NOT NULL,
    content             TEXT       NOT NULL,
    deleted             BOOLEAN NOT NULL DEFAULT 0,
    created_at          TIMESTAMP  NOT NULL    DEFAULT     CURRENT_TIMESTAMP(),
    updated_at          TIMESTAMP  NOT NULL    DEFAULT     CURRENT_TIMESTAMP()      ON UPDATE CURRENT_TIMESTAMP(),
    PRIMARY KEY(id),
    FOREIGN KEY(post_id) REFERENCES post(id)
);

CREATE TABLE user (
    id                  BINARY(16) NOT NULL,
    username            VARCHAR(50) NOT NULL,
    email               VARCHAR(100) NOT NULL,
    password_hash       VARCHAR(255) NOT NULL,
    created_at          TIMESTAMP  NOT NULL    DEFAULT     CURRENT_TIMESTAMP(),
    updated_at          TIMESTAMP  NOT NULL    DEFAULT     CURRENT_TIMESTAMP()      ON UPDATE CURRENT_TIMESTAMP(),
    PRIMARY KEY(id),
    UNIQUE(username),
    UNIQUE(email)
);
