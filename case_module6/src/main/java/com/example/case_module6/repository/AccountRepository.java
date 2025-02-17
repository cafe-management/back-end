package com.example.case_module6.repository;

import com.example.case_module6.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUserName(String userName);
}
