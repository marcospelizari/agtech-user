package com.agtech.alerta.dto;

import com.agtech.alerta.model.Alerta;

import java.time.LocalDateTime;

public record AlertaResponse(
        Long id,
        Integer usuarioId,
        String nomeUsuario,
        Long consumoId,
        String mensagem,
        LocalDateTime dataHora,
        Boolean resolvido
) {
    public static AlertaResponse from(Alerta a) {
        return new AlertaResponse(
                a.getId(),
                a.getUsuario().getId(),
                a.getUsuario().getName(),
                a.getConsumo() != null ? a.getConsumo().getId() : null,
                a.getMensagem(),
                a.getDataHora(),
                a.getResolvido()
        );
    }
}