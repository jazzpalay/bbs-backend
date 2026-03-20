package com.ryoga.bbs.infrastructure.database.command.log;

import com.ryoga.bbs.domain.model.log.Log;
import com.ryoga.bbs.domain.model.log.LogId;
import com.ryoga.bbs.domain.model.log.LogRepository;
import com.ryoga.bbs.domain.model.tag.LogTagIds;
import com.ryoga.bbs.domain.model.user.UserId;
import com.ryoga.bbs.infrastructure.database.command.log.RecordEntity.LogRecordEntity;
import com.ryoga.bbs.infrastructure.database.command.log.RecordEntity.LogTagsRecordEntity;
import com.ryoga.bbs.util.MySQLTool;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class LogDataSource implements LogRepository {

    private final LogMapper logMapper;
    private final LogTagsMapper logTagsMapper;

    public LogDataSource(LogMapper logMapper, LogTagsMapper logTagsMapper){
        this.logMapper = logMapper;
        this.logTagsMapper = logTagsMapper;
    }

    @Override
    public void saveLog(Log log){
        LogRecordEntity entity = new LogRecordEntity();
        entity.setId(MySQLTool.stringToBytes(log.getId().value()));
        entity.setUserId(MySQLTool.stringToBytes(log.getUserId().value()));
        entity.setTitle(log.getTitle().value());
        entity.setContent(log.getContent().value());
        entity.setLogDate(log.getLogDate().value());

        logMapper.save(entity);
    }

    @Override
    public void saveLogTags(LogTagIds list){
        LogTagsRecordEntity entity = new LogTagsRecordEntity(
                MySQLTool.stringToBytes(list.getLogId().value()),
                list.getTags()
                        .stream()
                        .map(tagId -> MySQLTool.stringToBytes(tagId.value()))
                        .collect(Collectors.toList())
        );

        logTagsMapper.save(entity);
    }

    @Override
    public void update(Log log){
        LogRecordEntity entity = new LogRecordEntity();
        entity.setId(MySQLTool.stringToBytes(log.getId().value()));
        entity.setUserId(MySQLTool.stringToBytes(log.getUserId().value()));
        entity.setTitle(log.getTitle().value());
        entity.setContent(log.getContent().value());
        entity.setLogDate(log.getLogDate().value());

        logMapper.update(entity);
    }

    @Override
    public void deleteLog(LogId logId, UserId userId){
        logMapper.delete(MySQLTool.stringToBytes(logId.value()), MySQLTool.stringToBytes(userId.value()));
    }

    @Override
    public void deleteLogTags(LogId logId){
        logTagsMapper.delete(MySQLTool.stringToBytes(logId.value()));
    }

    @Override
    public boolean existsById(LogId logId, UserId userId) {
        return Optional
                .ofNullable(logMapper.findLog(MySQLTool.stringToBytes(logId.value()), MySQLTool.stringToBytes(userId.value())))
                .isPresent();
    }
}
