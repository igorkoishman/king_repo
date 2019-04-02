package com.koishman.stocks.service;

import com.koishman.stocks.auth.repository.SymbolRepository;
import com.koishman.stocks.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class SymbolServiceImpl implements SymbolService {

	@Autowired
	private SymbolRepository symbolRepository;

	@Autowired
	private UserService userService;
}
