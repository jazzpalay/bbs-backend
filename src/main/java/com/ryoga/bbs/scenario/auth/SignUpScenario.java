package com.ryoga.bbs.scenario.auth;

import com.ryoga.bbs.domain.model.authentication.AuthenticationService;
import com.ryoga.bbs.domain.model.user.*;
import com.ryoga.bbs.domain.type.Id;
import com.ryoga.bbs.scenario.auth.command.SignUpCommand;
import com.ryoga.bbs.scenario.exception.DuplicateMailAddressScenarioException;
import com.ryoga.bbs.scenario.exception.DuplicateUserNameScenarioException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignUpScenario {

    private final AuthenticationService authenticationService;

    private final UserService userService;

    public SignUpScenario(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @Transactional
    public void signUp(SignUpCommand command) throws DuplicateUserNameScenarioException, DuplicateMailAddressScenarioException {
        // ユーザーIDを生成
        UserId userId = new UserId(Id.generate());

        // パスワードをハッシュ化
        HashedPassword hashedPassword = authenticationService.createHashPassword(command.getPassword());

        // ユーザーを作成
        User user = new User(
                userId,
                new UserName(command.getUserName()),
                new MailAddress(command.getMailAddress()),
                hashedPassword
        );

        // メールアドレスとユーザー名の重複チェック
        if(userService.existsByMailAddress(user.getMailAddress())) {
            throw new DuplicateMailAddressScenarioException("このメールアドレスは既に登録されています");
        }

        if (userService.existsByUsername(user.getUserName())) {
            throw new DuplicateUserNameScenarioException("このユーザー名は既に使用されています");
        }
        // ユーザーを保存
        userService.signUp(user);
    }
}
