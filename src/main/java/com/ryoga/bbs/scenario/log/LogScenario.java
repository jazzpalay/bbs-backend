package com.ryoga.bbs.scenario.log;

import com.ryoga.bbs.controller.api.log.response.LogDetailResponse;
import com.ryoga.bbs.controller.api.log.response.LogListResponse;
import com.ryoga.bbs.controller.api.log.response.LogResponse;
import com.ryoga.bbs.domain.model.log.*;
import com.ryoga.bbs.domain.model.tag.LogTagIds;
import com.ryoga.bbs.domain.model.tag.TagId;
import com.ryoga.bbs.domain.model.user.UserId;
import com.ryoga.bbs.domain.model.user.UserService;
import com.ryoga.bbs.domain.type.Id;
import com.ryoga.bbs.scenario.exception.LogNotFoundException;
import com.ryoga.bbs.scenario.exception.UserNotFoundException;
import com.ryoga.bbs.scenario.log.command.LogCommand;
import com.ryoga.bbs.scenario.log.queryService.LogQueryService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class LogScenario {

    private final LogService logService;
    private final LogQueryService logQueryService;
    private final UserService userService;

    public LogScenario(LogService logService, UserService userService, LogQueryService logQueryService) {
        this.logService = logService;
        this.logQueryService = logQueryService;
        this.userService = userService;
    }

    public void createLog(LogCommand logCommand){

        UserId userId = new UserId(Id.from(logCommand.getUserId()));

        if(!userService.existsById(userId)) {
            throw new UserNotFoundException("指定のユーザーは存在しません。");
        }

        LogId logId = new LogId(Id.generate());

        Log log = new Log(
                logId,
                userId,
                new Title(logCommand.getTitle()),
                new Content(logCommand.getContent()),
                new LogDate(logCommand.getLogDate())
        );

        LogTagIds logTagIds = new LogTagIds(
                logId,
                logCommand.getTagList()
                        .stream()
                        .map(tag -> new TagId(Id.from(tag)))
                        .collect(Collectors.toList())
        );

        logService.saveLog(log, logTagIds);
    }

    public LogListResponse getLogList(UserId userId) {
        if(!userService.existsById(userId)) {
            throw new UserNotFoundException("指定のユーザーは存在しません。");
        }
        return logQueryService.getLogList(userId.value());
    }

    public LogDetailResponse getLog(UserId userId, LogId logId) {
        if(!userService.existsById(userId)) {
            throw new UserNotFoundException("指定のユーザーは存在しません。");
        }

        if(!logService.existsById(logId, userId)) {
            throw new LogNotFoundException("指定のログは存在しません。");
        }
        return logQueryService.getLog(logId.value(), userId.value());
    }

    public void updateLog(LogCommand logCommand) {
        UserId userId = new UserId(Id.from(logCommand.getUserId()));
        LogId logId = new LogId(Id.from(logCommand.getLogId()));

        if(!userService.existsById(userId)) {
            throw new UserNotFoundException("指定のユーザーは存在しません。");
        }

        if(!logService.existsById(logId, userId)) {
            throw new LogNotFoundException("指定のログは存在しません。");
        }

        Log log = new Log(
                logId,
                userId,
                new Title(logCommand.getTitle()),
                new Content(logCommand.getContent()),
                new LogDate(logCommand.getLogDate())
        );

        LogTagIds logTagIds = new LogTagIds(
                logId,
                logCommand.getTagList()
                        .stream()
                        .map(tag -> new TagId(Id.from(tag)))
                        .collect(Collectors.toList())
        );

        logService.updateLog(log, logTagIds);
    }

    public void deleteLog(LogId logId, UserId userId) {
        if(!userService.existsById(userId)) {
            throw new UserNotFoundException("指定のユーザーは存在しません。");
        }

        if(!logService.existsById(logId, userId)) {
            throw new LogNotFoundException("指定のログは存在しません。");
        }

        logService.deleteLog(logId, userId);
    }
}
