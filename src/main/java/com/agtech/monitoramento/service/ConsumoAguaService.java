package com.agtech.monitoramento.service;

import com.agtech.alerta.model.Alerta;
import com.agtech.alerta.repository.AlertaRepository;
import com.agtech.core.exception.UserNotFoundException;
import com.agtech.monitoramento.dto.ConsumoAguaRequest;
import com.agtech.monitoramento.dto.ConsumoAguaResponse;
import com.agtech.monitoramento.model.ConsumoAgua;
import com.agtech.monitoramento.repository.ConsumoAguaRepository;
import com.agtech.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumoAguaService {

    private final ConsumoAguaRepository consumoRepo;
    private final UserRepository userRepo;
    private final AlertaRepository alertaRepo;

    public List<ConsumoAguaResponse> listarTodos() {
        return consumoRepo.findAll().stream()
                .map(ConsumoAguaResponse::from)
                .toList();
    }

    public List<ConsumoAguaResponse> listarPorUsuario(Integer usuarioId) {
        return consumoRepo.findByUsuarioId(usuarioId).stream()
                .map(ConsumoAguaResponse::from)
                .toList();
    }

    @Transactional
    public ConsumoAguaResponse registrar(ConsumoAguaRequest req) {
        var usuario = userRepo.findById(req.usuarioId())
                .orElseThrow(UserNotFoundException::new);

        ConsumoAgua consumo = ConsumoAgua.builder()
                .usuario(usuario)
                .volumeLitros(req.volumeLitros())
                .localMonitorado(req.localMonitorado())
                .limiteAlertaLitros(req.limiteAlertaLitros())
                .build();

        consumo = consumoRepo.save(consumo);

        // Dispara alerta automático se consumo ultrapassar o limite
        if (consumo.getVolumeLitros() > consumo.getLimiteAlertaLitros()) {
            Alerta alerta = Alerta.builder()
                    .usuario(usuario)
                    .consumo(consumo)
                    .mensagem(String.format(
                            "Consumo de %.1fL em '%s' ultrapassou o limite de %.1fL.",
                            consumo.getVolumeLitros(),
                            consumo.getLocalMonitorado(),
                            consumo.getLimiteAlertaLitros()))
                    .dataHora(LocalDateTime.now())
                    .resolvido(false)
                    .build();
            alertaRepo.save(alerta);
        }

        return ConsumoAguaResponse.from(consumo);
    }

    @Transactional
    public ConsumoAguaResponse atualizar(Long id, ConsumoAguaRequest req) {
        ConsumoAgua consumo = consumoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Consumo não encontrado: " + id));

        consumo.setVolumeLitros(req.volumeLitros());
        consumo.setLocalMonitorado(req.localMonitorado());
        consumo.setLimiteAlertaLitros(req.limiteAlertaLitros());

        return ConsumoAguaResponse.from(consumoRepo.save(consumo));
    }

    @Transactional
    public void deletar(Long id) {
        if (!consumoRepo.existsById(id)) {
            throw new RuntimeException("Consumo não encontrado: " + id);
        }
        consumoRepo.deleteById(id);
    }
}