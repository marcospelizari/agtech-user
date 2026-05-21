package com.agtech.monitoramento.dto;

import jakarta.validation.constraints.*;

public record ConsumoAguaRequest(
        @NotNull Integer usuarioId,

        @NotNull @DecimalMin("0.01") Double volumeLitros,

        @NotBlank String localMonitorado,

        @NotNull @DecimalMin("0.01") Double limiteAlertaLitros
) {}