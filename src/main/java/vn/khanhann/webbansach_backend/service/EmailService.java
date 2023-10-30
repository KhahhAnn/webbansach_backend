package vn.khanhann.webbansach_backend.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    public void sendEmail(String from, String to, String subject, String text);
}
