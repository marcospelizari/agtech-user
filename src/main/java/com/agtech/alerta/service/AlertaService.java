package com.agtech.alerta.service;

import com.agtech.alerta.dto.AlertaResponse;
import com.agtech.alerta.model.Alerta;
import com.agtech.alerta.repository.AlertaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertaService {

    private final AlertaRepository alertaRepo;

    public List<AlertaResponse> listarTodos() {
        return alertaRepo.findAll().stream()
                .map(AlertaResponse::from)
                .toList();
    }

    public List<AlertaResponse> listarAtivos() {
        return alertaRepo.findByResolvidoFalse().stream()
                .map(AlertaResponse::from)
                .toList();
    }

    @Transactional
    public AlertaResponse resolver(Long id) {
        Alerta alerta = alertaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado: " + id));
        alerta.setResolvido(true);
        return AlertaResponse.from(alertaRepo.save(alerta));
    }

    @Transactional
    public void deletar(Long id) {
        if (!alertaRepo.existsById(id)) {
            throw new RuntimeException("Alerta não encontrado: " + id);
        }
        alertaRepo.deleteById(id);
    }
}