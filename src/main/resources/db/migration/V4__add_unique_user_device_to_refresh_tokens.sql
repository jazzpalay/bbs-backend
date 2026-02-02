ALTER TABLE refresh_tokens
    ADD CONSTRAINT uk_refresh_tokens_user_device
        UNIQUE (user_id, device_id);