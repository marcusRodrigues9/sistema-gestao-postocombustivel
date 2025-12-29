package com.posto.mtcombustiveis.entity.dto;

import com.posto.mtcombustiveis.entity.BombaCombustivel;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
@Data
public class AbastecimentoRequest {

    @NotNull(message = "ID da bomba de combustível é obrigatório")

    @NotNull(message = "Quantidade de litros é obrigatória")
    @Positive(message = "Quantidade de litros deve ser maior que zero")
    private Long bombaCombustivelId;
    private Double quantidadeLitros;
}
