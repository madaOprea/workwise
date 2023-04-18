package com.user.service;

import com.user.model.dto.RequestedUserDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EmailService {

//    @Autowired
//    private JavaMailSender mailSender;

//    @Bean
//    public JavaMailSender getJavaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//
//        mailSender.setUsername("my.gmail@gmail.com");
//        mailSender.setPassword("password");
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//
//        return mailSender;
//    }

    public void sendPasswordResetEmail(RequestedUserDto userDto, String resetUrl) {
        String to = userDto.getEmail();
        String subject = "Password Reset Request";

        String body = "Dear " + userDto.getName() + ",\n\n";
        body += "You have requested a password reset for your account.\n\n";
        body += "To reset your password, please follow this link:\n";
        body += resetUrl + "\n\n";
        body += "If you did not request a password reset, please ignore this email.\n\n";
        body += "Best regards,\nThe Support Team";

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            //mailSender.send(message);
        } catch (Exception e) {
            log.error("/n *** " + this.getClass() + "*** error is: " + e.getMessage());
        }
    }
}
