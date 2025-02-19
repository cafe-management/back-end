package com.example.case_module6.controller;
import com.example.case_module6.dto.ChangePasswordRequest;
import com.example.case_module6.model.Account;
import com.example.case_module6.service.IAccountService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("api/login")
public class AccountRestController {
    @Value("${jwt.secret}")
    private String secretKey;
    @Autowired
    private IAccountService accountService;
    @PostMapping
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginRequest, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            String username = loginRequest.get("username");
            String password = loginRequest.get("password");
            boolean isValid = accountService.validateLogin(username, password);
            if (isValid) {
//                 Tạo token JWT khi login thành công
                String token = createJwtToken(username);
                String role = accountService.getRoleIdByUsername(username);
                request.getSession().setAttribute("username", username);
                response.put("success", true);
                response.put("message", "Đăng nhập thành công");
                response.put("role", role);
                response.put("token", token);
                response.put("username", username);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("success", false);
                response.put("message", "Sai tên đăng nhập hoặc mật khẩu......");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Lỗi khi xử lý yêu cầu");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    private String createJwtToken(String username) {
        long expirationTime = 1000 * 60 * 60 * 24; // 1 ngày (24h)
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
@PutMapping("/change-password")
public ResponseEntity<?> changePassword(
        @RequestBody ChangePasswordRequest changePasswordRequest,
        HttpServletRequest request) {

    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("username") == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bạn chưa đăng nhập!");
    }

    String username = (String) session.getAttribute("username");
    System.out.println("username hiện tại từ session: " + username);

    try {
        boolean isChanged = accountService.changePassword(username, changePasswordRequest.getOldPassword(), changePasswordRequest.getNewPassword());

        if (isChanged) {
            return ResponseEntity.ok("Mật khẩu đã được thay đổi thành công!");
        } else {
            return ResponseEntity.badRequest().body("Mật khẩu cũ không đúng hoặc có lỗi xảy ra.");
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi trong quá trình thay đổi mật khẩu.");
    }
}


}
