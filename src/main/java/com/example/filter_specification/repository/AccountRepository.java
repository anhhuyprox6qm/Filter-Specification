package com.example.filter_specification.repository;

import com.example.filter_specification.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findAccountByUsername(String username);
}
