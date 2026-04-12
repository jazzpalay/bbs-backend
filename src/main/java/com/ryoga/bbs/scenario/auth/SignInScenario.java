package com.ryoga.bbs.scenario.auth;

import com.ryoga.bbs.domain.model.authentication.AuthenticationService;
import com.ryoga.bbs.domain.model.authentication.DeviceId;
import com.ryoga.bbs.domain.model.authentication.RefreshToken;
import com.ryoga.bbs.domain.model.user.MailAddress;
import com.ryoga.bbs.domain.model.user.User;
import com.ryoga.bbs.domain.model.user.UserService;
import com.ryoga.bbs.domain.type.Id;
import com.ryoga.bbs.scenario.auth.command.SignInCommand;
import com.ryoga.bbs.scenario.exception.UserNotFoundException;
import com.ryoga.bbs.scenario.auth.result.SignInResult;
import org.springframework.stereotype.Service;

@Service
public class SignInScenario {

    private final AuthenticationService authenticationService;

    private final UserService userService;

    public SignInScenario(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    public SignInResult signIn(SignInCommand command) {

        DeviceId deviceId = new DeviceId(Id.from(command.getDeviceId()));
        MailAddress mailAddress = new MailAddress(command.getMailAddress());

        // メールアドレスが存在しているか
        if(!userService.existsByMailAddress(mailAddress)) {
            throw new UserNotFoundException("メールアドレスまたはパスワードが正しくありません");
        }

        User user = userService.getUserByMailAddress(mailAddress);
        // ハッシュパスワードの比較
        if(!authenticationService.checkPassword(command.getPassword(), user.getHashedPassword().value())) {
            throw new UserNotFoundException("メールアドレスまたはパスワードが正しくありません");
        }

        //同じユーザー、同じデバイスの既存のリフレッシュトークンを削除する
        if(authenticationService.existsUserIdAndDeviceId(user.getUserId(), deviceId)) {
            authenticationService.deleteRefreshToken(user.getUserId(), deviceId);
        }

        //アクセストークンjwt作成
        String accessToken = authenticationService.authenticate(user.getUserId().value());

        //リフレッシュトークン発行
        RefreshToken refreshToken = authenticationService.issueRefreshToken(deviceId, user.getUserId());

        return new SignInResult(accessToken, refreshToken.getToken());
    }
}
