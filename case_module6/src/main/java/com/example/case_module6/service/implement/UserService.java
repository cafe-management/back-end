package com.example.case_module6.service.implement;

import com.example.case_module6.model.Account;
import com.example.case_module6.model.News;
import com.example.case_module6.model.User;
import com.example.case_module6.repository.AccountRepository;
import com.example.case_module6.repository.UserRepository;
import com.example.case_module6.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;
    @Override
    public List getAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User entity) {
        if (entity.getAccount() == null) {
            entity.setAccount(new Account());
        }
        LocalDateTime time = LocalDateTime.now();
        String rawPassword = generateAndStorePassword(entity.getAccount().getUserName());
        String encodedPassword = passwordEncoder.encode(rawPassword);

        entity.getAccount().setPassword(encodedPassword);
        entity.getAccount().setDateCreatePassWord(time);

        Account savedAccount = accountRepository.save(entity.getAccount());
        entity.setAccount(savedAccount);
        System.out.println("Dữ liệu nhận được 2: " + entity);
        User savedUser = userRepository.save(entity);
        emailService.sendPasswordEmail(entity.getFullName(), entity.getEmail(), rawPassword, entity.getAccount().getUserName(), entity.getId());
        if (savedUser.getAccount() != null && savedUser.getAccount().getRole() != null) {
            System.out.println("Role từ entity: " + savedUser.getAccount().getRole().getNameRoles());
        } else {
            System.out.println("Role bị null!");
        }
    }

    @Override
    public void update(Long id, User entity) {
        LocalDateTime timeUpdate = LocalDateTime.now();
        entity.getAccount().setDateCreatePassWord(timeUpdate);
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            if (entity.getAddress() != null) {
                existingUser.setAddress(entity.getAddress());
            }
            if (entity.getPhoneNumber() != null) {
                existingUser.setPhoneNumber(entity.getPhoneNumber());
            }
            if (entity.getGender() != null){
                existingUser.setGender(entity.getGender());
            }
            if (entity.getAccount() != null && entity.getAccount().getPassword() != null) {
                String newPassword = entity.getAccount().getPassword();
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = passwordEncoder.encode(newPassword);
                existingUser.getAccount().setPassword(hashedPassword);
            }
        }
        userRepository.save(existingUser);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByAccount_UserName(username);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByAccount_UserName(username);
    }

    @Override
    public Page<User> findAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    private String generateAndStorePassword(String username) {
        String rawPassword = RandomStringUtils.randomAlphanumeric(8);
        // Lấy session từ RequestContextHolder
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpSession session = attributes.getRequest().getSession();
            session.setAttribute("rawPassword_" + username, rawPassword);
        }
        return rawPassword;
    }
}
