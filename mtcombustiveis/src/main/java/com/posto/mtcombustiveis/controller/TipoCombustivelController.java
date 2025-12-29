package com.posto.mtcombustiveis.controller;


import com.posto.mtcombustiveis.entity.TipoCombustivel;
import com.posto.mtcombustiveis.entity.dto.TipoCombustivelRequest;
import com.posto.mtcombustiveis.repository.ITipoCombustivelRepository;
import com.posto.mtcombustiveis.service.TipoCombustivelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoCombustivel")
public class TipoCombustivelController {

    private final TipoCombustivelService tipoCombustivelService;

    public TipoCombustivelController(TipoCombustivelService tipoCombustivelService){
        this.tipoCombustivelService = tipoCombustivelService;
    }

    //criar novo tipo de combustivel

    @PostMapping
    public ResponseEntity<TipoCombustivel> create(@Valid @RequestBody TipoCombustivelRequest request) {
        TipoCombustivel criado = tipoCombustivelService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }
    //listar todos tipos
    @GetMapping
    public ResponseEntity<List<TipoCombustivel>> findAll() {
        List<TipoCombustivel> lista = tipoCombustivelService.findAll();
        return ResponseEntity.ok(lista);
    }
    //listar unico combustivel pelo id
    @GetMapping("/{id}")
    public ResponseEntity<TipoCombustivel> findById(@PathVariable Long id) {
        TipoCombustivel tipoCombustivel = tipoCombustivelService.findById(id);
        return ResponseEntity.ok(tipoCombustivel);
    }
    //atualizar Tipo combustivel, atualiza o nome e preco
    @PutMapping("/{id}")
    public ResponseEntity<TipoCombustivel> update(@PathVariable Long id, @Valid @RequestBody TipoCombustivel tipoCombustivel) {
        TipoCombustivel atualizado = tipoCombustivelService.update(id, tipoCombustivel);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tipoCombustivelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
