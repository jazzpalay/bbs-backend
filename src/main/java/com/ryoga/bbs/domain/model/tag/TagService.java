package com.ryoga.bbs.domain.model.tag;

import com.ryoga.bbs.domain.model.user.UserId;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public void createTag(Tag tag) {
        tagRepository.save(tag);
    }

    public void updateTag(Tag tag) {
        tagRepository.update(tag);
    }

    public void deleteTag(TagId tagId) {
        tagRepository.delete(tagId);
    }

    public boolean existsName(UserId userId, TagName tagName) {
        return tagRepository.existsName(userId, tagName);
    }

    public boolean existsTag(TagId tagId) {
        return tagRepository.existsTag(tagId);
    }

    public TagList findAllByUserId(UserId userId) {
        return tagRepository.findAllByUserId(userId);
    }
}
