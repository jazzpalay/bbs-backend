package com.ryoga.bbs.domain.model.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(User user) {
        userRepository.save(user);
    }

    public boolean existsByUsername(UserName userName) {
        return userRepository.exists(userName);
    }

    public boolean existsByMailAddress(MailAddress mailAddress) {
        return userRepository.exists(mailAddress);
    }

}
