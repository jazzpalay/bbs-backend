package com.ryoga.bbs.controller.api.tag;

import com.ryoga.bbs.controller.api.tag.response.TagListResponse;
import com.ryoga.bbs.domain.model.tag.TagId;
import com.ryoga.bbs.domain.model.tag.UserTagList;
import com.ryoga.bbs.domain.model.user.UserId;
import com.ryoga.bbs.domain.type.Id;
import com.ryoga.bbs.scenario.tag.TagScenario;
import com.ryoga.bbs.scenario.tag.command.TagCommand;
import com.ryoga.bbs.controller.api.tag.form.TagForm;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {

    private final TagScenario tagScenario;

    public TagController(TagScenario tagScenario) {
        this.tagScenario = tagScenario;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createTag(Authentication authentication, @RequestBody @Valid TagForm tagForm) {
        String userId = authentication.getName();
        TagCommand command = TagCommand.toCommand(tagForm,userId);
        tagScenario.createTag(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<TagListResponse> findAllByUserId(Authentication authentication) {
        String userId = authentication.getName();
        UserTagList userTagList = tagScenario.findAllByUserId(new UserId(Id.from(userId)));
        return new ResponseEntity<>(TagListResponse.toResponse(userTagList), HttpStatus.OK);
    }

    @PutMapping("/{tagId}")
    public ResponseEntity<TagListResponse> updateTag(Authentication authentication, @PathVariable String tagId, @RequestBody @Valid TagForm tagForm) {
        String userId = authentication.getName();
        tagScenario.updateTag(TagCommand.toCommand(tagForm,userId), new TagId(Id.from(tagId)));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<Void> deleteTag(Authentication authentication, @PathVariable String tagId) {
        String userId = authentication.getName();
        tagScenario.deleteTag(new UserId(Id.from(userId)), new TagId(Id.from(tagId)));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
