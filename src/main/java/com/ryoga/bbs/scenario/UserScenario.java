package com.ryoga.bbs.scenario;

import com.ryoga.bbs.domain.model.user.User;
import com.ryoga.bbs.domain.model.user.UserId;
import com.ryoga.bbs.domain.model.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserScenario {

    private final UserService userService;

    public UserScenario(UserService userService) {
        this.userService = userService;
    }

    public User getMyUser(UserId userId) {
        return userService.getUserById(userId);
    }
}
