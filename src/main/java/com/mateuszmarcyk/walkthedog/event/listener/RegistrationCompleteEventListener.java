package com.mateuszmarcyk.walkthedog.event.listener;

import com.mateuszmarcyk.walkthedog.event.RegistrationCompleteEvent;
import com.mateuszmarcyk.walkthedog.user.User;
import com.mateuszmarcyk.walkthedog.user.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final UserService userService;
    private final JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

        User user = event.getUser();

        String verificationToken = UUID.randomUUID().toString();

        userService.saveVerificationToken(user, verificationToken);

        String url = event.getApplicationUrl() + "/register/verifyEmail?token=" + verificationToken;
        log.info("Click the link to complete your registration: {}", url);
        try {
            sendVerificationEmail(user, url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendVerificationEmail(User user, String url) throws MessagingException, UnsupportedEncodingException {

        String subject = "Email Verification";

        String senderName = "WalkTheDog";

        String mailContent = "<p> Hi, "+ user.getFirstName()+ ", </p>" +
                "<p>Thank you for registering with us." + "<br/>" +
                "Please, follow the link below to complete your registration.</p>" + "<br/>" +
                "<a href=\"" + url + "\">Verify your email to activate your account</a>" + "<br/>" +
                "<p> Thank you <br> Users Registration Portal Service";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("walkwithdog.contact@gmail.com", senderName);
        messageHelper.setTo(user.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);

    }
}
