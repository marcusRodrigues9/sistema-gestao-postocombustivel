package com.posto.mtcombustiveis.repository;

import com.posto.mtcombustiveis.entity.BombaCombustivel;
import com.posto.mtcombustiveis.entity.TipoCombustivel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBombaCombustivelRepository extends JpaRepository<BombaCombustivel, Long> {
    Optional<BombaCombustivel> findByNome(String nome);
    boolean existsByTipoCombustivelId(Long tipoCombustivelId);

}
