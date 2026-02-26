package com.ryoga.bbs.infrastructure.database.command.authentication.RecordEntity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RefreshTokenEntity {
    private byte[] id;
    private byte[] deviceId;
    private byte[] userId;
    private byte[] token;
    private LocalDateTime expiresAt;
    private boolean revoked;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
