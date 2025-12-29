package com.posto.mtcombustiveis.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(
        name = "tipo_combustivel",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "nome")
        }
)
public class TipoCombustivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 30)
    @NotBlank(message = "Nome do combustível é obrigatório")
    @Size(min = 3, max = 30, message = "Nome deve ter entre 3 e 30 caracteres")
    private String nome;

    @Column(name = "preco_por_litro", nullable = false)
    @NotNull(message = "Preço por litro é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    private Double precoPorLitro;
}