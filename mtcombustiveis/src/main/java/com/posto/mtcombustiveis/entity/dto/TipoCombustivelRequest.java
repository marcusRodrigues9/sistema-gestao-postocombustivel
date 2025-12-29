package com.posto.mtcombustiveis.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TipoCombustivelRequest {

    @NotBlank(message = "Nome do combustível é obrigatório")
    @Size(min = 3, max = 30, message = "Nome deve ter entre 3 e 30 caracteres")
    private String nome;

    @NotNull(message = "Preço por litro é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    private Double precoPorLitro;
}