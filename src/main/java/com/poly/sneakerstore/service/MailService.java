package com.poly.sneakerstore.service;

import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;

public interface MailService {
    void sendEmail(String recipients, String subject, String content, MultipartFile[] files) throws MessagingException;

    void sendLinkChangePassword(String emailTo, String code) throws MessagingException, UnsupportedEncodingException;
}
