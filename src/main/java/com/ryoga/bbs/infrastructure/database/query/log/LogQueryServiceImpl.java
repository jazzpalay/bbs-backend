package com.ryoga.bbs.infrastructure.database.query.log;

import com.ryoga.bbs.controller.api.log.response.LogResponse;
import com.ryoga.bbs.controller.api.tag.temp.TagResponse;
import com.ryoga.bbs.infrastructure.database.command.log.RecordEntity.LogListRecordEntity;
import com.ryoga.bbs.scenario.log.queryService.LogQueryService;
import com.ryoga.bbs.controller.api.log.response.LogListResponse;
import com.ryoga.bbs.infrastructure.database.command.log.LogMapper;
import com.ryoga.bbs.util.MySQLTool;
import org.springframework.stereotype.Service;

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

        return new LogListResponse(
                MySQLTool.bytesToString(entity.getUserId()),
                entity.getList()
                        .stream()
                        .map(log ->
                                new LogResponse(
                                        MySQLTool.bytesToString(log.getId()),
                                        log.getTitle(),
                                        log.getContent(),
                                        log.getLogDate().toString(),
                                        log.getTagList()
                                                .stream()
                                                .map(tag ->
                                                    new TagResponse(
                                                            MySQLTool.bytesToString(tag.getId()),
                                                            tag.getTagName(),
                                                            tag.getTagColor()
                                                    )
                                                ).collect(Collectors.toList())
                                )
                        ).collect(Collectors.toList())
        );
    }


}
