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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/check_account")
    public ResponseEntity<Map<String, Boolean>> checkAccount(@RequestParam(required = false) String email,
                                                             @RequestParam(required = false) String username) {
        boolean existsEmail = email != null && userService.existsByEmail(email);
        boolean existsUsername = username != null && userService.existsByUsername(username);
        Map<String, Boolean> response = new HashMap<>();
        response.put("existsEmail", existsEmail);
        response.put("existsUsername", existsUsername);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        System.out.println("Dữ liệu nhận được Account: " + user);
        if (user.getAccount() == null) {
            user.setAccount(new Account()); // Đảm bảo có account
        }
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            String email = request.get("email");
            String password = request.get("password");
            boolean isValid = userService.validateLogin(email, password);
            if (isValid) {
                response.put("success", true);
                response.put("message", "Đăng nhập thành công");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("success", false);
                response.put("message", "Sai email hoặc mật khẩu");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Lỗi khi xử lý yêu cầu");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
