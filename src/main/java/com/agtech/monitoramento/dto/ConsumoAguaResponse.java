package com.agtech.monitoramento.dto;

import com.agtech.monitoramento.model.ConsumoAgua;

import java.time.LocalDateTime;

public record ConsumoAguaResponse(
        Long id,
        Integer usuarioId,
        String nomeUsuario,
        Double volumeLitros,
        String localMonitorado,
        LocalDateTime dataHora,
        Double limiteAlertaLitros,
        boolean acimaDolimite
) {
    public static ConsumoAguaResponse from(ConsumoAgua c) {
        return new ConsumoAguaResponse(
                c.getId(),
                c.getUsuario().getId(),
                c.getUsuario().getName(),
                c.getVolumeLitros(),
                c.getLocalMonitorado(),
                c.getDataHora(),
                c.getLimiteAlertaLitros(),
                c.getVolumeLitros() > c.getLimiteAlertaLitros()
        );
    }
}