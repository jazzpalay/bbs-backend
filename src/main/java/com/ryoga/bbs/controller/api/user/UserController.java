package com.ryoga.bbs.controller.api.user;

import com.ryoga.bbs.controller.api.user.response.UserResponse;
import com.ryoga.bbs.domain.model.user.UserId;
import com.ryoga.bbs.domain.type.Id;
import com.ryoga.bbs.scenario.UserScenario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserScenario userScenario;

    public UserController(UserScenario userScenario) {
        this.userScenario = userScenario;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMyUser(Authentication authentication) {
        UserId userId = new UserId(Id.from(authentication.getName()));
        return ResponseEntity.ok(UserResponse.of(userScenario.getMyUser(userId)));
    }
}
