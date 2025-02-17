package com.example.case_module6.controller;

import com.example.case_module6.model.Account;
import com.example.case_module6.model.User;
import com.example.case_module6.service.IUserService;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;

import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UserRestConntroller {
    @Autowired
    private IUserService userService;

    @GetMapping("/admins")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/admins/check_account")
    public ResponseEntity<Map<String, Boolean>> checkAccount(@RequestParam(required = false) String email,
                                                             @RequestParam(required = false) String username) {
        boolean existsEmail = email != null && userService.existsByEmail(email);
        boolean existsUsername = username != null && userService.existsByUsername(username);
        Map<String, Boolean> response = new HashMap<>();
        response.put("existsEmail", existsEmail);
        response.put("existsUsername", existsUsername);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/admins")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        System.out.println("Dữ liệu nhận được Account: " + user);
        if (user.getAccount() == null) {
            user.setAccount(new Account()); // Đảm bảo có account
        }
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }


}
