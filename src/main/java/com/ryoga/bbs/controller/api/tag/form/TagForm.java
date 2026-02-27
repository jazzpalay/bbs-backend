package com.ryoga.bbs.controller.api.tag.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TagForm {
    private String tagName;
    private String tagColor;
}
