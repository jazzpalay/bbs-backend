package com.ryoga.bbs.scenario.tag;

import com.ryoga.bbs.domain.model.tag.*;
import com.ryoga.bbs.domain.model.user.UserId;
import com.ryoga.bbs.domain.model.user.UserService;
import com.ryoga.bbs.domain.type.Id;
import com.ryoga.bbs.scenario.exception.DuplicateTagNameScenarioException;
import com.ryoga.bbs.scenario.exception.UserNotFoundException;
import com.ryoga.bbs.scenario.tag.command.TagCommand;
import org.springframework.stereotype.Service;

@Service
public class TagScenario {
    private final TagService tagService;
    private final UserService userService;

    public TagScenario(TagService tagService, UserService userService) {
        this.tagService = tagService;
        this.userService = userService;
    }

    public void createTag(TagCommand tagCommand) {

        UserId userId = new UserId(Id.from(tagCommand.getUserId()));
        TagName tagName = new TagName(tagCommand.getTagName());

        if(!userService.existsById(userId)) {
            throw new UserNotFoundException("指定のユーザーは存在しません。");
        }

        if(tagService.exists(userId, tagName)){
            throw new DuplicateTagNameScenarioException("指定のタグは既に存在します。");
        }

        Tag tag = new Tag(
                new TagId(Id.generate()),
                userId,
                tagName,
                new TagColor(tagCommand.getTagColor())
        );
        tagService.createTag(tag);
    }

    public TagList findAllByUserId(UserId userId) {

        if(!userService.existsById(userId)) {
            throw new UserNotFoundException("指定のユーザーは存在しません。");
        }

        return tagService.findAllByUserId(userId);
    }
}
