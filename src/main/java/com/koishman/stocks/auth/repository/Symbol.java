package com.koishman.stocks.auth.repository;

import com.koishman.stocks.auth.model.User;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "symbol")
public class Symbol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String symbol;

	private String companyName;

	private boolean shouldRun;

	public boolean isShouldRun() {
		return shouldRun;
	}

	public void setShouldRun(boolean shouldRun) {
		this.shouldRun = shouldRun;
	}

	@ManyToMany(mappedBy = "symbols")
	private Set<User> users;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id)
				.append("symbol", symbol)
				.append("companyName", companyName)
				.append("shouldRun", shouldRun)
				.append("users", users)
				.toString();
	}
}
