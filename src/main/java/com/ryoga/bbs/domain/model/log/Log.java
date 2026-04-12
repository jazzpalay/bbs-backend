package com.ryoga.bbs.domain.model.log;

import com.ryoga.bbs.domain.model.user.UserId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
public class Log {
    private final LogId id;
    private final UserId userId;
    private final Title title;
    private final Content content;
    private final LogDate logDate;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    //新規作成
    public Log(LogId id, UserId userId, Title title, Content content, LogDate logDate){
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.logDate = logDate;
        this.createdAt = null;
        this.updatedAt = null;
    }

    //作成日更新日取得用
    public Log(
            LogId id,
            UserId userId,
            Title title,
            Content content,
            LogDate logDate,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.logDate = logDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


}
