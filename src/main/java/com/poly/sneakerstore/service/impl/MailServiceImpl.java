package com.poly.sneakerstore.service.impl;

import com.poly.sneakerstore.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.from}")
    private String from;

    @Value("${spring.mail.client-domain}")
    private String clientDomain;

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Override
    public void sendEmail(
            String recipients,
            String subject,
            String content,
            MultipartFile[] files
    ) throws MessagingException {
        log.info("Sending email to {}", recipients);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);

        if (recipients.contains(",")) {
            helper.setTo(InternetAddress.parse(recipients));
        } else {
            helper.setTo(recipients);
        }

        if (files != null) {
            for (MultipartFile file : files) {
                helper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()), file);
            }
        }

        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(mimeMessage);

    }

    @Override
    public void sendLinkChangePassword(String emailTo, String code) throws MessagingException, UnsupportedEncodingException {
        log.info("Sending link change password to {}", emailTo);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Context context = new Context();

        String linkChangePassword = String.format("%s/forgot/change-password?secretCode=%s&email=%s", clientDomain, code, emailTo);

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("linkChangePassword", linkChangePassword);
        context.setVariables(properties);

        helper.setFrom(from, "Sneaker Store");
        helper.setTo(emailTo);
        helper.setSubject("Confirm link change password");

        String html = templateEngine.process("change-password.html", context);
        helper.setText(html, true);
        mailSender.send(mimeMessage);
    }
}
