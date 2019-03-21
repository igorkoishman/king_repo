package com.koishman.stocks.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SymbolRepository extends JpaRepository<Symbol, Long> {

	List<Symbol> findAllByShouldRunTrue();

}
