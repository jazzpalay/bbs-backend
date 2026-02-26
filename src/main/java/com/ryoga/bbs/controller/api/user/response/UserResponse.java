package com.ryoga.bbs.controller.api.user.response;

import com.ryoga.bbs.domain.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponse {
    private String userName;
    private String mailAddress;

    public static UserResponse of(User user) {
        return new UserResponse(user.getUserName().value(), user.getMailAddress().value());
    }
}
