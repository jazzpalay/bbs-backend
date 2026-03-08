package com.ryoga.bbs.domain.model.tag;

import com.ryoga.bbs.domain.model.user.UserId;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository {
    void save(Tag tag);
    void update(Tag tag);
    void delete(TagId tagId);
    boolean existsName(UserId userId, TagName tagName);
    boolean existsTag(TagId tagId);
    TagList findAllByUserId(UserId userId);
}
