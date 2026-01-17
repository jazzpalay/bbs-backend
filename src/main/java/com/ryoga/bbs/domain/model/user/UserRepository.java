package com.ryoga.bbs.domain.model.user;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    void save(User user);

    User findById(UserId useId);

    User findByMailAddress(MailAddress mailAddress);

    boolean exists(MailAddress mailAddress);

    boolean exists(UserName userName);



}
