package com.agtech.monitoramento.model;

import com.agtech.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_consumo_agua")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ConsumoAgua {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Volume deve ser positivo")
    @Column(nullable = false)
    private Double volumeLitros;

    @NotBlank(message = "Local de monitoramento é obrigatório")
    @Column(nullable = false, length = 200)
    private String localMonitorado;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false)
    private Double limiteAlertaLitros;

    @PrePersist
    private void prePersist() {
        if (dataHora == null) dataHora = LocalDateTime.now();
    }
}