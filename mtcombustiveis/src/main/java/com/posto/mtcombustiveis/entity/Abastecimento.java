package com.posto.mtcombustiveis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "abastecimento")
public class Abastecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bomba_combustivel_id", nullable = false)
    @NotNull(message = "Bomba de combustível é obrigatória")
    @JsonIgnoreProperties({"abastecimentos"})
    private BombaCombustivel bombaCombustivel;

    @Column(name = "data_abastecimento", nullable = false)
    @NotNull(message = "Data do abastecimento é obrigatória")
    private LocalDateTime dataAbastecimento;

    @Column(name = "valor_total", nullable = false)
    @NotNull(message = "Valor total é obrigatório")
    @Positive(message = "Valor total deve ser maior que zero")
    private Double valorTotal;

    @Column(name = "quantidade_litros", nullable = false)
    @NotNull(message = "Quantidade de litros é obrigatória")
    @Positive(message = "Quantidade de litros deve ser maior que zero")
    private Double quantidadeLitros;
}