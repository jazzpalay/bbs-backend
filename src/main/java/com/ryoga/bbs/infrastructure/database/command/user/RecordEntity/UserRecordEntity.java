package com.ryoga.bbs.infrastructure.database.command.user.RecordEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserRecordEntity {

    private byte[] id;
    private String username;
    private String email;
    private String passwordHash;
}
