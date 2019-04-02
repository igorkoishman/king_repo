package com.koishman.stocks.auth.service;

import com.koishman.stocks.auth.model.User;
import com.koishman.stocks.auth.repository.RoleRepository;
import com.koishman.stocks.auth.repository.Symbol;
import com.koishman.stocks.auth.repository.SymbolRepository;
import com.koishman.stocks.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private SymbolRepository symbolRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	@Override
	public void addSymbolsToUser(User user, Set<Symbol> symbols) {
		Set<Symbol> userSymbols = user.getSymbols();
		userSymbols.addAll(symbols);
		user.setSymbols(userSymbols);
		userRepository.save(user);
	}

}
