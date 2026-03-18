package com.ryoga.bbs.controller.api.log.form;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LogForm {
    private String title;
    private String content;
    private String logDate;
    private List<String> tagIds;
}
