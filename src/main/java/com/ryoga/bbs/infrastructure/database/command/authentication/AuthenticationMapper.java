package com.ryoga.bbs.infrastructure.database.command.authentication;

import com.ryoga.bbs.infrastructure.database.command.authentication.RecordEntity.RefreshTokenRecordEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthenticationMapper {
    void save(RefreshTokenRecordEntity refreshTokenRecordEntity);
    RefreshTokenRecordEntity findUserIdAndDeviceId(byte[] userId, byte[] deviceId);
    void revokeRefreshToken(byte[] userId, byte[] deviceId);
    void deleteRefreshToken(byte[] userId, byte[] deviceId);
    RefreshTokenRecordEntity findByRefreshToken(byte[] refreshToken);
}
