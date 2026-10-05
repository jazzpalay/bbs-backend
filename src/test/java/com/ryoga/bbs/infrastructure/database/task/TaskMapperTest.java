package com.ryoga.bbs.infrastructure.database.task;

import com.ryoga.bbs.infrastructure.database.command.task.RecordEntity.TaskRecordEntity;
import com.ryoga.bbs.infrastructure.database.command.task.TaskMapper;
import com.ryoga.bbs.infrastructure.database.query.task.TaskQueryMapper;
import com.ryoga.bbs.util.MySQLTool;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MapperScan({
        "com.ryoga.bbs.infrastructure.database.command.task",
        "com.ryoga.bbs.infrastructure.database.query.task"
})
class TaskMapperTest {

    // V2__Insert_Initial_data.sqlでシードされる既存ユーザー
    private static final String EXISTING_USER_ID = "d61b5447-0cf0-4355-88cf-1d1589bf6546";

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    TaskQueryMapper taskQueryMapper;

    @Test
    void 保存したタスクが一覧取得できる(){
        byte[] taskId = MySQLTool.stringToBytes(UUID.randomUUID().toString());
        byte[] userId = MySQLTool.stringToBytes(EXISTING_USER_ID);

        TaskRecordEntity entity = new TaskRecordEntity();
        entity.setId(taskId);
        entity.setUserId(userId);
        entity.setTaskName("テストタスク");
        entity.setDescription("テスト用の説明");
        entity.setStatus("IN_PROGRESS");
        entity.setPriority("HIGH");
        entity.setStartedDate(LocalDate.of(2026, 5, 20));
        entity.setDueDate(LocalDate.of(2026, 5, 28));
        entity.setCompletedDate(null);

        taskMapper.save(entity);

        var list = taskQueryMapper.findAllTask(userId);

        assertEquals(1, list.size());
        assertEquals("テストタスク", list.get(0).getTaskName());
        assertEquals("テスト用の説明", list.get(0).getDescription());
        assertEquals("IN_PROGRESS", list.get(0).getStatus());
        assertEquals("HIGH", list.get(0).getPriority());
        assertEquals(LocalDate.of(2026, 5, 20), list.get(0).getStartedDate());
        assertEquals(LocalDate.of(2026, 5, 28), list.get(0).getDueDate());
        assertNull(list.get(0).getCompletedDate());
    }

    @Test
    void タスクが存在しない場合は空リストが返る(){
        byte[] userId = MySQLTool.stringToBytes(EXISTING_USER_ID);

        var list = taskQueryMapper.findAllTask(userId);

        assertTrue(list.isEmpty());
    }
}
