package com.ryoga.bbs.scenario;

import com.ryoga.bbs.domain.authentication.AuthenticationService;
import com.ryoga.bbs.domain.model.user.*;
import com.ryoga.bbs.domain.type.Id;
import com.ryoga.bbs.scenario.command.SignInCommand;
import com.ryoga.bbs.scenario.command.SignUpCommand;
import com.ryoga.bbs.scenario.exception.DuplicateMailAddressScenarioException;
import com.ryoga.bbs.scenario.exception.DuplicateUserNameScenarioException;
import com.ryoga.bbs.scenario.exception.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpScenario {

    private final AuthenticationService authenticationService;

    private final UserService userService;

    public SignUpScenario(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

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

    public String signIn(SignInCommand command) {

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

        //jwt作成
        return authenticationService.authenticate(user.getUserId().value());
    }
}
