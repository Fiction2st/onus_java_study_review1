package com.onus.crud_review1.services;

public interface EmailService {
    void sendEmail(String to, String name);
    void sendVerificationCodeEmail(String to, String code);
}
