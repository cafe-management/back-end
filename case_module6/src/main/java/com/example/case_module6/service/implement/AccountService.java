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

import java.util.List;

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
        System.out.println("Mật khẩu gốc: " + password);
        System.out.println("Mật khẩu mã hóa trong database: " + account.getPassword());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, account.getPassword());
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public boolean changePassword(String userName, String oldPassword, String newPassword, String oldPasswordRaw) {
        Account account = accountRepository.findByUserName(userName);

        if (account == null) {
            System.out.println("Không tìm thấy tài khoản");
            return false;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // 🟢 Kiểm tra mật khẩu thô từ session trước
        if (oldPasswordRaw != null && oldPasswordRaw.equals(oldPassword)) {
            System.out.println("✅ Mật khẩu khớp với mật khẩu thô trong session");
        } else {
            // 🟡 Kiểm tra với mật khẩu đã mã hóa trong database
            if (!encoder.matches(oldPassword, account.getPassword())) {
                System.out.println("❌ Mật khẩu cũ không đúng");
                return false;
            }
        }

        // 🟢 Mã hóa mật khẩu mới và cập nhật vào database
        account.setPassword(encoder.encode(newPassword));
        accountRepository.save(account);

        System.out.println("✅ Đổi mật khẩu thành công");
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
