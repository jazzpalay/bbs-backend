package com.ryoga.bbs.controller.api.task.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TaskListResponse {
    private String userId;
    private List<TaskResponse> list;
}
