package com.ryoga.bbs.domain.authentication;

import com.ryoga.bbs.domain.model.user.HashedPassword;
import com.ryoga.bbs.util.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public AuthenticationService(PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public HashedPassword createHashPassword(String rowPassword) {
        return new HashedPassword(passwordEncoder.encode(rowPassword));
    }

    public boolean checkPassword(String rowPassword, String hashPassword) {
        return passwordEncoder.matches(rowPassword, hashPassword);
    }

    public String authenticate(String userId) {
        return jwtProvider.createToken(userId);
    }

}
