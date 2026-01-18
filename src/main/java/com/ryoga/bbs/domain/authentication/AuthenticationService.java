package com.ryoga.bbs.domain.authentication;

import com.ryoga.bbs.domain.model.user.HashedPassword;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public HashedPassword createHashPassword(String rowPassword) {
        return new HashedPassword(passwordEncoder.encode(rowPassword));
    }

    public boolean checkPassword(String rowPassword, String hashPassword) {
        return passwordEncoder.matches(rowPassword, hashPassword);
    }

}
