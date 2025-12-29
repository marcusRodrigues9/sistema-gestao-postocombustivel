package com.posto.mtcombustiveis.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BombaCombustivelRequest {

    @NotBlank(message = "Nome da bomba é obrigatório")
    @Size(min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres")
    private String nome;

    @NotNull(message = "ID do tipo de combustível é obrigatório")
    private Long tipoCombustivelId;
}