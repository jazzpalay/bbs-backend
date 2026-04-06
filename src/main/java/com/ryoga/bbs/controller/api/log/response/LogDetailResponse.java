package com.ryoga.bbs.controller.api.log.response;

import com.ryoga.bbs.controller.api.tag.response.TagResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LogDetailResponse {
    private String logId;
    private String userId;
    private String title;
    private String content;
    private String logDate;
    private List<TagResponse> tags;
}
