package com.ryoga.bbs.domain.model.authentication;

import com.ryoga.bbs.domain.model.user.HashedPassword;
import com.ryoga.bbs.domain.model.user.UserId;
import com.ryoga.bbs.domain.type.Id;
import com.ryoga.bbs.util.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthenticationService {

    private final AuthenticationRepository authenticationRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final Clock clock;

    public AuthenticationService(AuthenticationRepository authenticationRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtProvider jwtProvider,
                                 Clock clock) {
        this.authenticationRepository = authenticationRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.clock = clock;
    }

    public HashedPassword createHashPassword(String rowPassword) {
        return new HashedPassword(passwordEncoder.encode(rowPassword));
    }

    public boolean checkPassword(String rowPassword, String hashPassword) {
        return passwordEncoder.matches(rowPassword, hashPassword);
    }

    public String authenticate(String userId) {
        return jwtProvider.createToken(userId);
    }

    public RefreshToken issueRefreshToken(DeviceId deviceId, UserId userId){
        //トークンID生成
        TokenId tokenId = new TokenId(Id.generate());

        //トークン文字列生成
        String token = UUID.randomUUID().toString();

        //期限の計算（二週間）
        LocalDateTime now = LocalDateTime.now(clock);
        LocalDateTime expiresAt = now.plusDays(14);

        RefreshToken refreshToken = RefreshToken.issue(
                tokenId,
                deviceId,
                userId,
                token,
                expiresAt
        );

        //リフレッシュトークン永続化
        authenticationRepository.save(refreshToken);

        return refreshToken;
    }

    public boolean existsUserIdAndDeviceId(UserId userId, DeviceId deviceId) {
        return authenticationRepository.findUserIdAndDeviceId(userId, deviceId);
    }

    public void deleteRefreshToken(UserId userId, DeviceId deviceId) {
        authenticationRepository.deleteRefreshToken(userId, deviceId);
    }

    public RefreshToken findByRefreshToken(String refreshToken) {
        return authenticationRepository.findByRefreshToken(refreshToken);
    }

    public void revokeExistingRefreshToken(UserId userId, DeviceId deviceId){
        authenticationRepository.revokeRefreshToken(userId, deviceId);
    }
}
