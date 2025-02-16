package com.example.case_module6.service.implement;

import com.example.case_module6.model.Account;
import com.example.case_module6.model.User;
import com.example.case_module6.repository.AccountRepository;
import com.example.case_module6.repository.UserRepository;
import com.example.case_module6.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public List getAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User entity) {
        if (entity.getAccount() == null) {
            entity.setAccount(new Account()); // Tạo account mới nếu null
        }

        // Tạo mật khẩu tự động
        LocalDateTime time = LocalDateTime.now();
        String rawPassword = RandomStringUtils.randomAlphanumeric(8);
        String encodedPassword = passwordEncoder.encode(rawPassword);

        entity.getAccount().setPassword(encodedPassword);
        entity.getAccount().setDateCreatePassWord(time);

        // Lưu account trước khi lưu user
        Account savedAccount = accountRepository.save(entity.getAccount());
        entity.setAccount(savedAccount);

        // Lưu user
        userRepository.save(entity);

        System.out.println("Mật khẩu gốc: " + rawPassword);
    }

    @Override
    public void update(Long id, User entity) {
        LocalDateTime timeUpdate = LocalDateTime.now();
        entity.getAccount().setDateCreatePassWord(timeUpdate);
        userRepository.save(entity);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }


}
