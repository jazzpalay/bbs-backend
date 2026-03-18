package com.ryoga.bbs.scenario.log.queryService;

import com.ryoga.bbs.controller.api.log.response.LogListResponse;

public interface LogQueryService {
    LogListResponse getLogList(String userId);
}
