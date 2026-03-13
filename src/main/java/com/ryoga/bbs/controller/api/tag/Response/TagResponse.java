package com.ryoga.bbs.controller.api.tag.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TagResponse {
    private String tagId;
    private String tagName;
    private String tagColor;
}
