package com.posto.mtcombustiveis.controller;

import com.posto.mtcombustiveis.entity.Abastecimento;
import com.posto.mtcombustiveis.entity.dto.AbastecimentoRequest;
import com.posto.mtcombustiveis.service.AbastecimentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abastecimentos")
public class AbastecimentoController {

    private final AbastecimentoService abastecimentoService;

    public AbastecimentoController(AbastecimentoService abastecimentoService) {
        this.abastecimentoService = abastecimentoService;
    }


    // Criar novo abastecimento
    @PostMapping
    public ResponseEntity<Abastecimento> registrar(@Valid @RequestBody AbastecimentoRequest request) {
        Abastecimento abastecimento = abastecimentoService.registrarAbastecimento(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(abastecimento);
    }

    // Listar todos os abastecimentos
    @GetMapping
    public ResponseEntity<List<Abastecimento>> listarTodos() {
        return ResponseEntity.ok(abastecimentoService.listarTodos());
    }

    // Buscar abastecimento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Abastecimento> buscarPorId(@PathVariable Long id) {
        Abastecimento abastecimento = abastecimentoService.buscarPorId(id);
        return ResponseEntity.ok(abastecimento);
    }

    // Deletar abastecimento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        abastecimentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}