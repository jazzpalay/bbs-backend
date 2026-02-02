package com.ryoga.bbs.infrastructure.database.command.user;

import com.ryoga.bbs.infrastructure.database.command.user.RecordEntity.UserRecordEntity;
import com.ryoga.bbs.util.MySQLTool;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MapperScan("com.ryoga.bbs.infrastructure.database.command.user")
class UserMapperTest {

    @Autowired
    UserMapper mapper;

    @Container
    static MySQLContainer<?> mySql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("bbs")
            .withUsername("bbsuser")
            .withPassword("bbspass");

    @DynamicPropertySource
    static void overRideProps(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mySql::getJdbcUrl);
        registry.add("spring.datasource.username", mySql::getUsername);
        registry.add("spring.datasource.password", mySql::getPassword);
    }

    @Nested
    class findIdById{
        @Test
        void 存在する場合(){
            UserRecordEntity entity = mapper.findUserById(MySQLTool.stringToBytes("d61b5447-0cf0-4355-88cf-1d1589bf6546"));
            assertEquals("d61b5447-0cf0-4355-88cf-1d1589bf6546", MySQLTool.bytesToString(entity.getId()));
        }

        @Test
        void 存在しない場合(){
            UserRecordEntity entity = mapper.findUserById(MySQLTool.stringToBytes("d61b5447-1234-4355-88cf-1d1589bf6546"));
            assertNull(entity);
        }
    }

    @Nested
    class findIdByMailAddress{
        @Test
        void 存在する場合(){
            UserRecordEntity entity = mapper.findUserByMailAddress("jazzmaster@gmail.com");
            assertEquals("d61b5447-0cf0-4355-88cf-1d1589bf6546", MySQLTool.bytesToString(entity.getId()));
        }

        @Test
        void 存在しない場合(){
            UserRecordEntity entity = mapper.findUserByMailAddress("nonexistent@example.com");
            assertNull(entity);
        }
    }

    @Nested
    class findByUserName {
        @Test
        void 存在する場合() {
            UserRecordEntity entity = mapper.findUserByUserName("ryoga_gt");
            assertEquals("d61b5447-0cf0-4355-88cf-1d1589bf6546", MySQLTool.bytesToString(entity.getId()));
            assertEquals("ryoga_gt", entity.getUsername());
        }

        @Test
        void 存在しない場合() {
            UserRecordEntity entity = mapper.findUserByUserName("nonexistentuser");
            assertNull(entity);
        }
    }

    @Nested
    class saveUser {
        @Test
        void 新規ユーザーを保存できる() {
            // テスト用の新しいユーザーを作成
            UserRecordEntity newUser = new UserRecordEntity();
            newUser.setId(MySQLTool.stringToBytes("123e4567-e89b-12d3-a456-426614174000"));
            newUser.setUsername("newuser");
            newUser.setEmail("newuser@example.com");
            newUser.setPasswordHash("hashedpassword");

            // ユーザーを保存
            mapper.save(newUser);

            // 保存したユーザーを取得して検証
            UserRecordEntity savedUser = mapper.findUserById(MySQLTool.stringToBytes("123e4567-e89b-12d3-a456-426614174000"));
            assertNotNull(savedUser);
            assertEquals("newuser", savedUser.getUsername());
            assertEquals("newuser@example.com", savedUser.getEmail());
            assertEquals("hashedpassword", savedUser.getPasswordHash());
        }
    }
}