package com.example.case_module6.controller;

//import com.example.case_module6.dto.ChangePasswordRequest;
import com.example.case_module6.model.Account;
import com.example.case_module6.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("api/login")
public class AccountRestController {
    @Autowired
    private IAccountService accountService;
    @PostMapping
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            String username = request.get("username");
            String password = request.get("password");
            boolean isValid = accountService.validateLogin(username, password);
            if (isValid) {
                response.put("success", true);
                response.put("message", "Đăng nhập thành công");
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
//    @PutMapping("/change-password")
//    public ResponseEntity<?> changePassword(
//            @RequestBody ChangePasswordRequest changePasswordRequest) {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName(); // Lấy tên người dùng hiện tại
//
//        try {
//            boolean isChanged = accountService.changePassword(changePasswordRequest.getOldPassword(), changePasswordRequest.getNewPassword());
//
//            if (isChanged) {
//                return ResponseEntity.ok("Mật khẩu đã được thay đổi thành công!");
//            } else {
//                return ResponseEntity.badRequest().body("Mật khẩu cũ không đúng hoặc có lỗi xảy ra.");
//            }
//        } catch (Exception e) {
//            // Trường hợp có lỗi bất ngờ xảy ra
//            String response = "Đã xảy ra lỗi trong quá trình thay đổi mật khẩu. Vui lòng thử lại.";
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }

}
