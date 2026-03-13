package com.ryoga.bbs.scenario.tag.command;

import com.ryoga.bbs.controller.api.tag.form.TagForm;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TagCommand {
    private String userId;
    private String tagName;
    private String tagColor;

    public static TagCommand toCommand(TagForm form, String userId) {
        return new TagCommand(userId,form.getTagName(), form.getTagColor());
    }
}
