package com.ryoga.bbs.infrastructure.database.command.user;

import com.ryoga.bbs.infrastructure.database.command.user.RecordEntity.UserRecordEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void save(UserRecordEntity entity);
    UserRecordEntity findUserById(byte[] userId);
    UserRecordEntity findUserByMailAddress(String mailAddress);
    UserRecordEntity findUserByUserName(String userName);

}
