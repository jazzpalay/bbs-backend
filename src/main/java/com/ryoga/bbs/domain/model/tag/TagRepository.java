package com.ryoga.bbs.domain.model.tag;

import com.ryoga.bbs.domain.model.user.UserId;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository {
    void save(Tag tag);
    void delete(TagId tagId);
    boolean exists(UserId userId, TagName tagName);
    TagList findAllByUserId(UserId userId);
}
