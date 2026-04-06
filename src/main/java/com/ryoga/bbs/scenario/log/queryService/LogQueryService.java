package com.ryoga.bbs.scenario.log.queryService;

import com.ryoga.bbs.controller.api.log.response.LogDetailResponse;
import com.ryoga.bbs.controller.api.log.response.LogListResponse;
import com.ryoga.bbs.controller.api.log.response.LogResponse;

public interface LogQueryService {
    LogListResponse getLogList(String userId);
    LogDetailResponse getLog(String logId, String userId);
}
