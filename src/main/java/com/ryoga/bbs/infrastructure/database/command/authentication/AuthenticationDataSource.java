package com.ryoga.bbs.infrastructure.database.command.authentication;

import com.ryoga.bbs.domain.model.authentication.AuthenticationRepository;
import com.ryoga.bbs.domain.model.authentication.DeviceId;
import com.ryoga.bbs.domain.model.authentication.RefreshToken;
import com.ryoga.bbs.domain.model.user.UserId;
import com.ryoga.bbs.infrastructure.database.command.authentication.RecordEntity.RefreshTokenEntity;
import com.ryoga.bbs.util.MySQLTool;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AuthenticationDataSource implements AuthenticationRepository {

    private final AuthenticationMapper authenticationMapper;

    public AuthenticationDataSource(AuthenticationMapper authenticationMapper) {
        this.authenticationMapper = authenticationMapper;
    }

    @Override
    public void save(RefreshToken refreshToken) {
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
        refreshTokenEntity.setId(MySQLTool.stringToBytes(refreshToken.getTokenId().value()));
        refreshTokenEntity.setDeviceId(MySQLTool.stringToBytes(refreshToken.getDeviceId().value()));
        refreshTokenEntity.setUserId(MySQLTool.stringToBytes(refreshToken.getUserId().value()));
        refreshTokenEntity.setToken(MySQLTool.stringToBytes(refreshToken.getToken()));
        refreshTokenEntity.setExpiresAt(refreshToken.getExpiresAt());
        refreshTokenEntity.setRevoked(refreshToken.isRevoked());

        authenticationMapper.save(refreshTokenEntity);
    }

    @Override
    public boolean findUserIdAndDeviceId(UserId userId, DeviceId deviceId) {
        return Optional.ofNullable(authenticationMapper
                        .findUserIdAndDeviceId(MySQLTool.stringToBytes(userId.value()), MySQLTool.stringToBytes(deviceId.value())))
                .isPresent();
    }

    @Override
    public void deleteRefreshToken(UserId userId, DeviceId deviceId) {
        authenticationMapper.deleteRefreshToken(MySQLTool.stringToBytes(userId.value()), MySQLTool.stringToBytes(deviceId.value()));
    }

    @Override
    public void revokeRefreshToken(UserId userId, DeviceId deviceId) {
        authenticationMapper.revokeRefreshToken(MySQLTool.stringToBytes(userId.value()), MySQLTool.stringToBytes(deviceId.value()));
    }
}
