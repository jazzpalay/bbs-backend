package com.ryoga.bbs.infrastructure.database.command.log;

import com.ryoga.bbs.domain.model.log.Log;
import com.ryoga.bbs.domain.model.log.LogRepository;
import com.ryoga.bbs.domain.model.tag.LogTagIds;
import com.ryoga.bbs.infrastructure.database.command.log.RecordEntity.LogRecordEntity;
import com.ryoga.bbs.infrastructure.database.command.log.RecordEntity.LogTagsRecordEntity;
import com.ryoga.bbs.util.MySQLTool;
import org.springframework.stereotype.Repository;

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
    public void save(Log log){
        LogRecordEntity entity = new LogRecordEntity(
                MySQLTool.stringToBytes(log.getId().value()),
                MySQLTool.stringToBytes(log.getUserId().value()),
                log.getTitle().value(),
                log.getContent().value(),
                log.getLogDate().value(),
                null
        );
        logMapper.save(entity);
    }

    @Override
    public void save(LogTagIds list){
        LogTagsRecordEntity entity = new LogTagsRecordEntity(
                MySQLTool.stringToBytes(list.getLogId().value()),
                list.getTags()
                        .stream()
                        .map(tagId -> MySQLTool.stringToBytes(tagId.value()))
                        .collect(Collectors.toList())
        );

        logTagsMapper.save(entity);
    }
}
