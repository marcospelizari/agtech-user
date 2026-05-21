package com.agtech.monitoramento.repository;

import com.agtech.monitoramento.model.ConsumoAgua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumoAguaRepository extends JpaRepository<ConsumoAgua, Long> {
    List<ConsumoAgua> findByUsuarioId(Integer usuarioId);
    List<ConsumoAgua> findByVolumeLitrosGreaterThan(Double limite);
}