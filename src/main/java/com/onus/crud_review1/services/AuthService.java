package com.onus.crud_review1.services;

public interface AuthService {
    String sendVerificationCode(EmailDTO emailDTO);
    boolean verifyEmailCode(EmailVerifyRequestDTO emailVerifyRequestDTO);
}
