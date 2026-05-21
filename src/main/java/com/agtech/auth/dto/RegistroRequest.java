package com.agtech.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistroRequest(
        @NotBlank(message = "Nome é obrigatório")
        String name,

        @Email @NotBlank(message = "E-mail é obrigatório")
        String email,

        @NotBlank @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        String password
) {}