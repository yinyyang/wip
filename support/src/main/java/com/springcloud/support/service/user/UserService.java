package com.springcloud.support.service.user;

import com.springcloud.support.model.auth.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
