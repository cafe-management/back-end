package com.example.case_module6.service.implement;

import com.example.case_module6.model.Account;
import com.example.case_module6.model.User;
import com.example.case_module6.repository.AccountRepository;
import com.example.case_module6.service.IAccountService;
import com.example.case_module6.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public boolean validateLogin(String username, String password) {
        Account account = accountRepository.findByUserName(username);
        if (account == null) {
            return false;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, account.getPassword());
    }
}
