package com.ryoga.bbs.controller.api.log.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class LogListResponse {
    private String userId;
    private List<LogResponse> list;
}
