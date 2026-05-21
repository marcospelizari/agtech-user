package com.agtech.monitoramento.resource;

import com.agtech.monitoramento.dto.ConsumoAguaRequest;
import com.agtech.monitoramento.dto.ConsumoAguaResponse;
import com.agtech.monitoramento.service.ConsumoAguaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumo-agua")
@RequiredArgsConstructor
public class ConsumoAguaController {

    private final ConsumoAguaService service;

    @GetMapping
    public ResponseEntity<List<ConsumoAguaResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/historico/{usuarioId}")
    public ResponseEntity<List<ConsumoAguaResponse>> historicoPorUsuario(
            @PathVariable Integer usuarioId) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }

    @PostMapping
    public ResponseEntity<ConsumoAguaResponse> registrar(
            @Valid @RequestBody ConsumoAguaRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumoAguaResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ConsumoAguaRequest req) {
        return ResponseEntity.ok(service.atualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}