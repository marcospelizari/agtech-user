package com.agtech.auth.dto;

public record LoginResponse(String token, String tipo, String email) {
    public LoginResponse(String token, String email) {
        this(token, "Bearer", email);
    }
}