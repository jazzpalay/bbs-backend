package com.ryoga.bbs.scenario;

import com.ryoga.bbs.domain.model.user.User;
import com.ryoga.bbs.domain.model.user.UserId;
import com.ryoga.bbs.domain.model.user.UserService;
import com.ryoga.bbs.scenario.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserScenario {

    private final UserService userService;

    public UserScenario(UserService userService) {
        this.userService = userService;
    }

    public User getMyUser(UserId userId) {
        if(!userService.existsById(userId)) {
            throw new UserNotFoundException("ユーザーが存在しません");
        }
        return userService.getUserById(userId);
    }
}
