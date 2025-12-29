package com.posto.mtcombustiveis.repository;

import com.posto.mtcombustiveis.entity.Abastecimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAbastecimentoRepository extends JpaRepository<Abastecimento, Long> {
    boolean existsByBombaCombustivelId(Long id);
}
