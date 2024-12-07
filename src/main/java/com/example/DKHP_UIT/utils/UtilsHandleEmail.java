package com.example.DKHP_UIT.utils;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.example.DKHP_UIT.exception.ExceptionCode;
import com.example.DKHP_UIT.exception.ExceptionStudent;
import com.example.DKHP_UIT.response.ResponseCode;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
public class UtilsHandleEmail {

    @Autowired
    private JavaMailSender javaMailSender; // use an object of the lib of mail in maven.
    @Value("${spring.mail.username}")
    private String sender; // get sender from file application.properties

    private String recipient;
    private String msgBody;
    private String subject;

    // send HTML email
    public void sendHtmlEmail() {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom(sender);
            helper.setTo(this.recipient);
            helper.setSubject(this.subject);
            helper.setText(this.msgBody, true); // 'true' indicates that this is an HTML email.

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new ExceptionStudent(ExceptionCode.SendEmailFail);
        }
    }

    public void sendCreateAccount(String email, String subject, String mssv, String password) {
        this.recipient = email;
        this.subject = subject;
        this.msgBody = createAccountBody(mssv, password);
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom(sender);
            helper.setTo(this.recipient);
            helper.setSubject(this.subject);
            helper.setText(this.msgBody, true); // 'true' indicates that this is an HTML email.

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new ExceptionStudent(ExceptionCode.SendEmailFail);
        }
    }

    // function create Random code
    public String createRandom() {
        Random rd = new Random();
        int x1 = rd.nextInt(9 - 1 + 1) + 1;
        int x2 = rd.nextInt(9 - 0 + 0) + 0;
        int x3 = rd.nextInt(9 - 0 + 0) + 0;
        int x4 = rd.nextInt(9 - 0 + 0) + 0;

        String code = x1 + "" + x2 + "" + x3 + "" + x4;

        return code;
    }

    public String createAccountBody(String mssv, String password) {
        return "<html>" +
                "<head>" +
                "<!-- Fonts -->" +
                "<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">" +
                "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>" +
                "<link href=\"https://fonts.googleapis.com/css2?family=Itim&display=swap\" rel=\"stylesheet\">" +
                "<link href=\"https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&family=Itim&display=swap\" rel=\"stylesheet\">"
                +
                "<style>" +
                "body p {" +
                "font-family: \"Inter\", sans-serif;" +
                "font-optical-sizing: auto;" +
                "font-style: normal;" +
                "}" +
                "* {" +
                "margin: 0;" +
                "padding: 0;" +
                "box-sizing: border-box;" +
                "}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div style=\"padding: 10px 10px; box-shadow: 2px 2px 12px gray; width: fit-content;\">" +
                "<p style=\"font-weight: 600; font-style: italic;\">XIN CHÀO BẠN, EMAIL ĐƯỢC GỬI TỪ VĂN PHÒNG UIT.</p>"
                +
                "<br/>" +
                "<p style=\"color: black; width: 400px;\">Chúng tôi đã tạo cho bạn 1 tài khoản để đăng nhập vào trang web đăng kí học phần UIT.</p>"
                +
                "<a href=\"http://127.0.0.1:5500/src/main/java/com/example/DKHP_UIT/utils/html.html\">Website dkhp UIT.</a>"
                +
                "<br/><br/>" +
                "<div>" +
                "<ul style=\"list-style-type: disc; padding-left: 20px;\">" +
                "<li style=\"display: flex; align-items: center; margin: 0;\">" +
                "<span style=\"margin-right: 5px;\">•</span>" +
                "<p style=\"margin: 0;\">Tài khoản: </p>" +
                "<p style=\"margin-left: 5px; font-weight: 600;\">" + mssv + "</p>" +
                "</li>" +
                "<li style=\"display: flex; align-items: center; margin: 0;\">" +
                "<span style=\"margin-right: 5px;\">•</span>" +
                "<p style=\"margin: 0;\">Mật khẩu: </p>" +
                "<p style=\"margin-left: 5px; font-weight: 600;\">" + password + "</p>" +
                "</li>" +
                "</ul>" +
                "</div>" +
                "<br/>" +
                "<p style=\"font-style: italic; color: red;\">Email này được tạo tự động - vui lòng không trả lời! Cám ơn.</p>"
                +
                "</div>" +
                "</body>" +
                "</html>";
    }

}