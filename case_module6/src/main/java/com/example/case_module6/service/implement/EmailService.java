package com.example.case_module6.service.implement;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    private String getSignature() {
        return "<br><br>--<br>" +
                "<strong style='font-size: 14px; color: #2c3e50;'>Trân trọng,</strong><br>" +
                "<strong style='font-size: 16px; color: #E67E22;'>Dana Coffee</strong><br>" +
                "<span style='font-size: 14px; color: #34495E;'>📍 Địa chỉ: 295 Nguyễn Tất Thành, Thanh Bình, Hải Châu, Đà Nẵng</span><br>" +
                "<span style='font-size: 14px; color: #34495E;'>📞 Số điện thoại: <a href='tel:+84364773446' style='color: #2980B9;'>+84 364 773 446</a></span><br>" +
                "<span style='font-size: 14px; color: #34495E;'>✉ Email: <a href='mailto:finestdana@gmail.com' style='color: #2980B9;'>finestdana@gmail.com</a></span>";
    }
    public void sendPasswordEmail(String fullName, String toEmail, String rawPassword, String username, Long employId) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("THÔNG TIN TÀI KHOẢN NHÂN VIÊN _" + fullName + "_ MÃ NHÂN VIÊN " + employId);
            // Nội dung email với HTML
            StringBuilder emailContent = new StringBuilder();
            emailContent.append("<p>Xin chào, <strong>").append(fullName).append("</strong></p>");
            emailContent.append("<p>Thông báo: Tài khoản đã được cấp và sẵn sàng để bạn bắt đầu công việc, dưới đây là thông tin tên tài khoản và mật khẩu của bạn: </p>");
            emailContent.append("<p><strong>Tên tài khoản:</strong> <span style='color: blue;'>").append(username).append("</span></p>");
            emailContent.append("<p><strong>Mật khẩu tài khoản của bạn:</strong> <span style='color: red;'>").append(rawPassword).append("</span></p>");
            emailContent.append("<p>Vui lòng đăng nhập và thay đổi mật khẩu của bạn sau khi đăng nhập lần đầu tiên.</p>");
            emailContent.append(getSignature());

            helper.setText(emailContent.toString(), true);
            mailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }

    }
}
