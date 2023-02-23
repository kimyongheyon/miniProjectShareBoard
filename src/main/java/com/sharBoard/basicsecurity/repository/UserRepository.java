package com.sharBoard.basicsecurity.repository;

import com.sharBoard.basicsecurity.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);
}
