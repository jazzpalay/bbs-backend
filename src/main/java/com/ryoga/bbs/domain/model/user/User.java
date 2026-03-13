package com.ryoga.bbs.domain.model.user;

import com.ryoga.bbs.domain.type.DomainLogicInvalidException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EqualsAndHashCode
@Getter
public class User {
    private final UserId userId;
    private final UserName userName;
    private final MailAddress mailAddress;
    private final HashedPassword hashedPassword;

    public User(UserId userId, UserName userName, MailAddress mailAddress, HashedPassword password) {
        if(userId == null) {
            throw new DomainLogicInvalidException("ユーザーIDは必須です");
        }
        if(userName == null) {
            throw new DomainLogicInvalidException("ユーザー名は必須です");
        }
        if(mailAddress == null) {
            throw new DomainLogicInvalidException("メールアドレスは必須です");
        }
        if (password == null) {
            throw new DomainLogicInvalidException("パスワードは必須です");
        }

        this.userId = userId;
        this.userName = userName;
        this.mailAddress = mailAddress;
        this.hashedPassword = password;
    }
}
