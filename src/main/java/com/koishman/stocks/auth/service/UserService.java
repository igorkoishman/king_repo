package com.koishman.stocks.auth.service;

import com.koishman.stocks.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
