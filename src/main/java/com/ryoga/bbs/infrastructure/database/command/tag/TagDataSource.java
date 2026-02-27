package com.ryoga.bbs.infrastructure.database.command.tag;

import com.ryoga.bbs.domain.model.tag.*;
import com.ryoga.bbs.domain.model.user.UserId;
import com.ryoga.bbs.domain.type.Id;
import com.ryoga.bbs.infrastructure.database.command.tag.RecordEntity.TagRecordEntity;
import com.ryoga.bbs.util.MySQLTool;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TagDataSource implements TagRepository {

    private final TagMapper tagMapper;

    public TagDataSource(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Override
    public void save(Tag tag) {
        TagRecordEntity tagRecordEntity = new TagRecordEntity(
                MySQLTool.stringToBytes(tag.getId().value()),
                MySQLTool.stringToBytes(tag.getUserId().value()),
                tag.getTagName().value(),
                tag.getTagColor().value()
        );
        tagMapper.save(tagRecordEntity);
    }

    @Override
    public void delete(TagId tagId){
        tagMapper.delete(MySQLTool.stringToBytes(tagId.value()));
    }

    @Override
    public boolean exists(UserId userId, TagName tagName){
        return Optional.ofNullable(
                tagMapper.findByUserIdAndTagName(MySQLTool.stringToBytes(userId.value()), tagName.value())
        ).isPresent();
    }

    @Override
    public TagList findAllByUserId(UserId userId){
        List<TagRecordEntity> list = tagMapper.findAllByUserId(MySQLTool.stringToBytes(userId.value()));
        return new TagList(
                userId,
                list.stream().map(entity ->
                new Tag(
                        new TagId(Id.from(MySQLTool.bytesToString(entity.getId()))),
                        new UserId(Id.from(MySQLTool.bytesToString(entity.getUserId()))),
                        new TagName(entity.getTagName()),
                        new TagColor(entity.getTagColor())
                )
        ).collect(Collectors.toList()));
    }
}
