package com.posto.mtcombustiveis.controller;

import com.posto.mtcombustiveis.entity.BombaCombustivel;
import com.posto.mtcombustiveis.entity.TipoCombustivel;
import com.posto.mtcombustiveis.entity.dto.BombaCombustivelRequest;
import com.posto.mtcombustiveis.service.BombaCombustivelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bombaCombustivel")
public class BombaCombustivelController {

    @Autowired
    private BombaCombustivelService bombaCombustivelService;

    //criar nova Bomba de combustivel
    @PostMapping
    public ResponseEntity<BombaCombustivel> create(@Valid @RequestBody BombaCombustivelRequest request) {
        BombaCombustivel bomba = bombaCombustivelService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(bomba);
    }
    //listar todas
    @GetMapping
    public ResponseEntity<List<BombaCombustivel>> findAll(){
        List<BombaCombustivel> lista = bombaCombustivelService.findAll();
        return ResponseEntity.ok().body(lista);
    }
    //listar unica Bomba de combustivel pelo id
    @GetMapping("/{id}")
    public ResponseEntity<BombaCombustivel> findById(@PathVariable Long id){
        BombaCombustivel existente = bombaCombustivelService.findById(id);
        return ResponseEntity.ok(existente);
    }
    //atualizar Bomba combustivel, atualiza o nome e o tipo de combustivel
    @PutMapping("/{id}")
    public ResponseEntity<BombaCombustivel> update(@PathVariable Long id,  @Valid @RequestBody BombaCombustivelRequest request){
        BombaCombustivel atualizado = bombaCombustivelService.update(id, request);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BombaCombustivel> delete(@PathVariable Long id) {
        bombaCombustivelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
