package com.onus.crud_review1.controllers;

import com.onus.crud_review1.dtos.ApiResponseDTO;
import com.onus.crud_review1.dtos.EmailDTO;
import com.onus.crud_review1.dtos.EmailVerifyRequestDTO;
import com.onus.crud_review1.dtos.user.UserDTO;
import com.onus.crud_review1.dtos.user.UserResponseDTO;
import com.onus.crud_review1.services.AuthService;
import com.onus.crud_review1.services.EmailService;
import com.onus.crud_review1.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final EmailService emailService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> signUpUser(@Valid @RequestBody UserDTO userDTO) {
        UserResponseDTO savedUser = userService.createUser(userDTO);

        // 회원정보 저장 후 가입안내 메일 발송 추가
        emailService.sendEmail(savedUser.getEmail(), savedUser.getUserName());

        // 생성 response 정규화
        ApiResponseDTO<UserResponseDTO> response = ApiResponseDTO.<UserResponseDTO>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("회원가입성공")
                .data(savedUser)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/send/email")
    public ResponseEntity<ApiResponseDTO<Boolean>> sendEmail(
            @Valid
            @RequestBody EmailDTO emailDTO){
        authService.sendVerificationCode(emailDTO);

        ApiResponseDTO<Boolean> response = ApiResponseDTO.<Boolean>builder()
                .statusCode(HttpStatus.OK.value())
                .message("메일발송성공")
                .data(true)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify/email")
    public ResponseEntity<ApiResponseDTO<Boolean>> verifyEmail(
            @Valid
            @RequestBody EmailVerifyRequestDTO emailVerifyRequestDTO){

        Boolean verified = authService.verifyEmailCode(emailVerifyRequestDTO);

        ApiResponseDTO<Boolean> response = ApiResponseDTO.<Boolean>builder()
                .statusCode(HttpStatus.OK.value())
                .message("메일 인증 성공")
                .data(verified)
                .build();

        return ResponseEntity.ok(response);
    }
}
