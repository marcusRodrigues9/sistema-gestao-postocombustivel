package com.posto.mtcombustiveis.repository;

import com.posto.mtcombustiveis.entity.TipoCombustivel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITipoCombustivelRepository extends JpaRepository<TipoCombustivel, Long> {
    Optional<TipoCombustivel> findByNome(String nome);
}
