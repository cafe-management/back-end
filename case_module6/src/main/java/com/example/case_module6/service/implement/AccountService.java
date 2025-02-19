package com.example.case_module6.service.implement;

import com.example.case_module6.model.Account;
import com.example.case_module6.model.User;
import com.example.case_module6.repository.AccountRepository;
import com.example.case_module6.service.IAccountService;
import com.example.case_module6.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public boolean validateLogin(String username, String password) {
        Account account = accountRepository.findByUserName(username);
        System.out.println("Đăng nhập với username: " + username);
        if (account == null) {
            System.out.println("Không tìm thấy tài khoản với username: " + username);
            return false;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, account.getPassword());
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword) {
        // Lấy thông tin người dùng đã đăng nhập từ SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Tên đăng nhập của người dùng hiện tại

        // Lấy tài khoản từ cơ sở dữ liệu
        Account account = accountRepository.findByUserName(username);

        if (account == null) {
            // Nếu không tìm thấy tài khoản
            System.out.println("Không tìm thấy tài khoản");
            return false;
        }
        // Sử dụng BCrypt để kiểm tra mật khẩu cũ
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(oldPassword, account.getPassword())) {
            // Nếu mật khẩu cũ không khớp
            System.out.println("Mật khẩu cũ không đúng");
            return false;
        }

        // Cập nhật mật khẩu mới
        account.setPassword(encoder.encode(newPassword));
        accountRepository.save(account); // Lưu lại tài khoản với mật khẩu đã thay đổi

        System.out.println("Đổi mật khẩu thành công");
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

}
