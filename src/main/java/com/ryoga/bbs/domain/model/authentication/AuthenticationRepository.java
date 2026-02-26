package com.ryoga.bbs.domain.model.authentication;

import com.ryoga.bbs.domain.model.user.UserId;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository {
    void save(RefreshToken refreshToken);
    boolean findUserIdAndDeviceId(UserId userId, DeviceId deviceId);
    void revokeRefreshToken(UserId userId, DeviceId deviceId);
    void deleteRefreshToken(UserId userId, DeviceId deviceId);
    RefreshToken findByRefreshToken(String refreshToken);
}
