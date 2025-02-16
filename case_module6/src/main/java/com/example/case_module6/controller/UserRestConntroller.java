package com.example.case_module6.controller;

import com.example.case_module6.model.Account;
import com.example.case_module6.model.User;
import com.example.case_module6.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admins")
public class UserRestConntroller {
    @Autowired
    private IUserService userService;
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        System.out.println("Dữ liệu nhận được: " + user);
        if (user.getAccount() == null) {
            user.setAccount(new Account()); // Đảm bảo có account
        }
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
