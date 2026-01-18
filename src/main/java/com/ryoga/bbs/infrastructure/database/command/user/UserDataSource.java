package com.ryoga.bbs.infrastructure.database.command.user;

import com.ryoga.bbs.domain.model.user.*;
import com.ryoga.bbs.domain.type.Id;
import com.ryoga.bbs.infrastructure.database.DataBaseException;
import com.ryoga.bbs.infrastructure.database.command.user.RecordEntity.UserRecordEntity;
import com.ryoga.bbs.util.MySQLTool;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDataSource implements UserRepository {

    private final UserMapper userMapper;

    public UserDataSource(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {
        UserRecordEntity entity = new UserRecordEntity();
        entity.setId(MySQLTool.stringToBytes(user.getUserId().value()));
        entity.setUsername(user.getUserName().value());
        entity.setEmail(user.getMailAddress().value());
        entity.setPasswordHash(user.getHashedPassword().value());

        userMapper.save(entity);
    }

    @Override
    public User findById(UserId useId) {
        UserRecordEntity entity = Optional.ofNullable(userMapper.findUserById(MySQLTool.stringToBytes(useId.value())))
                .orElseThrow(() -> new DataBaseException("ユーザーが存在しません"));
        return toUser(entity);
    }

    @Override
    public User findByMailAddress(MailAddress mailAddress) {
        UserRecordEntity entity = Optional.ofNullable(userMapper.findUserByMailAddress(mailAddress.value()))
                .orElseThrow(() -> new DataBaseException("ユーザーが存在しません"));
        return toUser(entity);
    }

    @Override
    public boolean exists(UserName userName) {
        return Optional.ofNullable(userMapper.findUserByUserName(userName.value())).isPresent();
    }

    @Override
    public boolean exists(MailAddress mailAddress) {
        return Optional.ofNullable(userMapper.findUserByMailAddress(mailAddress.value())).isPresent();
    }

    private User toUser(UserRecordEntity entity) {
        return new User(
                new UserId(Id.from(MySQLTool.bytesToString(entity.getId()))),
                new UserName(entity.getUsername()),
                new MailAddress(entity.getEmail()),
                new HashedPassword(entity.getPasswordHash())
        );
    }

}
