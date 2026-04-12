package com.ryoga.bbs.infrastructure.database.query.log;

import com.ryoga.bbs.controller.api.log.response.LogDetailResponse;
import com.ryoga.bbs.controller.api.log.response.LogResponse;
import com.ryoga.bbs.controller.api.tag.response.TagResponse;
import com.ryoga.bbs.infrastructure.database.command.log.RecordEntity.LogListRecordEntity;
import com.ryoga.bbs.infrastructure.database.command.log.RecordEntity.LogRecordEntity;
import com.ryoga.bbs.scenario.log.queryService.LogQueryService;
import com.ryoga.bbs.controller.api.log.response.LogListResponse;
import com.ryoga.bbs.infrastructure.database.command.log.LogMapper;
import com.ryoga.bbs.util.MySQLTool;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class LogQueryServiceImpl implements LogQueryService {

    private final LogMapper logMapper;

    public LogQueryServiceImpl(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    @Override
    public LogListResponse getLogList(String userId) {
        LogListRecordEntity entity = logMapper.findAllLog(MySQLTool.stringToBytes(userId));

        if (entity == null) {
            return new LogListResponse(
                    userId,
                    Collections.emptyList()
            );
        }

        return new LogListResponse(
                MySQLTool.bytesToString(entity.getUserId()),
                entity.getList()
                        .stream()
                        .map(log -> {
                            LogResponse response = new LogResponse();
                            response.setLogId(MySQLTool.bytesToString(log.getId()));
                            response.setTitle(log.getTitle());
                            response.setLogDate(log.getLogDate().toString());
                            response.setTags(
                                    log.getTagList()
                                            .stream()
                                            .map(tag ->
                                                    new TagResponse(
                                                            MySQLTool.bytesToString(tag.getId()),
                                                            tag.getTagName(),
                                                            tag.getTagColor()
                                                    )
                                            ).collect(Collectors.toList())
                            );
                            return response;
                        }).collect(Collectors.toList())
        );
    }

    @Override
    public LogDetailResponse getLog(String logId, String userId) {
        LogRecordEntity entity = logMapper.findLog(MySQLTool.stringToBytes(logId), MySQLTool.stringToBytes(userId));

        LogDetailResponse response = new LogDetailResponse();
        response.setLogId(MySQLTool.bytesToString(entity.getId()));
        response.setUserId(MySQLTool.bytesToString(entity.getUserId()));
        response.setTitle(entity.getTitle());
        response.setContent(entity.getContent());
        response.setLogDate(entity.getLogDate().toString());
        response.setTags(
                entity.getTagList()
                        .stream()
                        .map(tag ->
                                new TagResponse(
                                        MySQLTool.bytesToString(tag.getId()),
                                        tag.getTagName(),
                                        tag.getTagColor()
                                )
                        ).collect(Collectors.toList())
        );
        return response;
    }
}
