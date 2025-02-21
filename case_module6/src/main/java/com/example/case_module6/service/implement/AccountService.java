package com.example.case_module6.service.implement;

import com.example.case_module6.model.Account;
import com.example.case_module6.model.User;
import com.example.case_module6.repository.AccountRepository;
import com.example.case_module6.repository.UserRepository;
import com.example.case_module6.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private OtpService otpService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public boolean validateLogin(String username, String password) {
        Account account = accountRepository.findByUserName(username);
        if (account == null) {
            return false;
        }
        return passwordEncoder.matches(password, account.getPassword());
    }

    @Override
    public boolean changePassword(String userName, String oldPassword, String newPassword, String oldPasswordRaw) {
        Account account = accountRepository.findByUserName(userName);
        if (account == null) {
            return false;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (oldPasswordRaw != null && oldPasswordRaw.equals(oldPassword)) {
        } else {
            if (!encoder.matches(oldPassword, account.getPassword())) {
                return false;
            }
        }
        account.setPassword(encoder.encode(newPassword));
        accountRepository.save(account);
        return true;
    }
    @Override
    public String getRoleIdByUsername(String username) {
        Account account = accountRepository.findByUserName(username);
        if (account != null) {
            return account.getRole().getNameRoles();
        }
        return null;
    }
    @Override
    public Map<String, Object> forgotPassword(String emailOrUsername) {
       boolean usernameOpt = userRepository.existsByAccount_UserName(emailOrUsername);
       boolean emailOpt = userRepository.existsByEmail(emailOrUsername);
       if(!usernameOpt && !emailOpt){
           return Map.of("success", false, "message", "Không tìm thấy tài khoản");
       }
       User user = usernameOpt ? userRepository.findByAccount_UserName(emailOrUsername) : userRepository.findByEmail(emailOrUsername);
        Account account = user.getAccount();
        String otp = String.format("%06d", new Random().nextInt(999999));
        otpService.saveOtp(emailOrUsername, otp);
        emailService.sendOtpEmail(user.getFullName(), user.getEmail(), otp);
        return Map.of("success", true, "message", "Mã OTP đã được gửi đến email của bạn");
    }

    @Override
    public Map<String, Object> verifyOtp(String emailOrUsername, String otp) {
        if(otpService.validateOtp(emailOrUsername, otp)){
            return Map.of("success", true, "message", "OTP hợp lệ, bạn có thể đổi mật khẩu");
        }
        return Map.of("success", false, "message", "OTP không hợp lệ hoặc đã hết hạn");
    }

    @Override
    public Map<String,Object> newPassword(String emailOrUsername, String password) {
        boolean usernameExists = userRepository.existsByAccount_UserName(emailOrUsername);
        boolean emailExists = userRepository.existsByEmail(emailOrUsername);

        if (!usernameExists && !emailExists) {
            return Map.of("success", false, "message", "Không tìm thấy tài khoản");
        }
        User user = usernameExists
                ? userRepository.findByAccount_UserName(emailOrUsername)
                : userRepository.findByEmail(emailOrUsername);
        Account account = user.getAccount();
        account.setPassword(passwordEncoder.encode(password));
        accountRepository.save(account);
        return Map.of("success", true, "message", "Mật khẩu đã được cập nhật thành công");
    }


}
