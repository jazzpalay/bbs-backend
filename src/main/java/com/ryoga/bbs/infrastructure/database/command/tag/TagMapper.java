package com.ryoga.bbs.infrastructure.database.command.tag;

import com.ryoga.bbs.infrastructure.database.command.tag.RecordEntity.TagRecordEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {
    void save(TagRecordEntity tagRecordEntity);
    void update(TagRecordEntity tagRecordEntity);
    void delete(byte[] tagId);
    TagRecordEntity findByUserIdAndTagName(byte[] userId, String tagName);
    TagRecordEntity findByTagId(byte[] tagId);
    List<TagRecordEntity> findAllByUserId(byte[] userId);
}
