package com.ryoga.bbs.domain.model.authentication;

import com.ryoga.bbs.domain.model.user.UserId;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RefreshToken {

    private final TokenId tokenId;
    private final DeviceId deviceId;
    private final UserId userId;
    private final String token;
    private final LocalDateTime expiresAt;
    private boolean revoked;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private RefreshToken(
            TokenId tokenId,
            DeviceId deviceId,
            UserId userId,
            String token,
            LocalDateTime expiresAt
    ) {
        this.tokenId = tokenId;
        this.deviceId = deviceId;
        this.userId = userId;
        this.token = token;
        this.expiresAt = expiresAt;
        this.revoked = false;
        this.createdAt = null;
        this.updatedAt = null;
    }

    public static RefreshToken issue(
            TokenId tokenId,
            DeviceId deviceId,
            UserId userId,
            String token,
            LocalDateTime expiresAt) {
        return new RefreshToken(tokenId, deviceId, userId, token, expiresAt);
    }

    public void revoke() {
        this.revoked = true;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isExpired() {
        return this.expiresAt.isBefore(LocalDateTime.now());
    }
}
