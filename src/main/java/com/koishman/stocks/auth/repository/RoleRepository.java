package com.koishman.stocks.auth.repository;

import com.koishman.stocks.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
