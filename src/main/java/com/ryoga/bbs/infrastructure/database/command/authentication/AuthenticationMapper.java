package com.ryoga.bbs.infrastructure.database.command.authentication;

import com.ryoga.bbs.domain.model.authentication.DeviceId;
import com.ryoga.bbs.domain.model.user.UserId;
import com.ryoga.bbs.infrastructure.database.command.authentication.RecordEntity.RefreshTokenEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthenticationMapper {
    void save(RefreshTokenEntity refreshTokenEntity);
    RefreshTokenEntity findUserIdAndDeviceId(byte[] userId, byte[] deviceId);
    void revokeRefreshToken(byte[] userId, byte[] deviceId);
    void deleteRefreshToken(byte[] userId, byte[] deviceId);
}
