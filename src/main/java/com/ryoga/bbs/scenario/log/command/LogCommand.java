package com.ryoga.bbs.scenario.log.command;

import com.ryoga.bbs.controller.api.log.form.LogForm;
import com.ryoga.bbs.controller.api.tag.form.TagForm;
import com.ryoga.bbs.scenario.tag.command.TagCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LogCommand {
    private String logId;
    private String userId;
    private String title;
    private String content;
    private String logDate;
    private List<String> tagList;

    public static LogCommand toCreateCommand(LogForm form, String userId) {
        LogCommand command = new LogCommand();
        command.setUserId(userId);
        command.setTitle(form.getTitle());
        command.setContent(form.getContent());
        command.setLogDate(form.getLogDate());
        command.setTagList(form.getTagIds());

        return command;
    }

    public  static LogCommand toUpdateCommand(LogForm form, String logId, String userId) {
        LogCommand command = new LogCommand();
        command.setLogId(logId);
        command.setUserId(userId);
        command.setTitle(form.getTitle());
        command.setContent(form.getContent());
        command.setLogDate(form.getLogDate());
        command.setTagList(form.getTagIds());

        return command;
    }
}
