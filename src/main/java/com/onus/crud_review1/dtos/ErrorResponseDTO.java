package com.onus.crud_review1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO<T> {
    private int statusCode;
    private String message;
    private LocalDateTime timestamp;
    private T data;

}