package com.ryoga.bbs.controller.api.tag.temp;

import com.ryoga.bbs.domain.model.tag.UserTagList;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class TagListResponse {
    private String userId;
    private List<TagResponse> list;

    public static TagListResponse toResponse(UserTagList list){
        return new TagListResponse(
                list.getUserId().value(),
                list.getTags().stream().map(
                        tag -> new TagResponse(
                                tag.getId().value(),
                                tag.getTagName().value(),
                                tag.getTagColor().value()
                        )
                ).collect(Collectors.toList())
        );
    }

}
