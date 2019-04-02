package com.koishman.stocks.auth.service;

import com.koishman.stocks.auth.model.User;
import com.koishman.stocks.auth.repository.Symbol;

import java.util.Set;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

	void addSymbolsToUser(User user, Set<Symbol> symbols);
}
