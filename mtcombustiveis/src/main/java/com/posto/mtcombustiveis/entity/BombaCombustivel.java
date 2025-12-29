package com.posto.mtcombustiveis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(
        name = "bomba_combustivel",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "nome")
        }
)
public class BombaCombustivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    @NotBlank(message = "Nome da bomba é obrigatório")
    @Size(min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres")
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "combustivel_id", nullable = false)
    @NotNull(message = "Tipo de combustível é obrigatório")
    @JsonIgnoreProperties({"bombas"})
    private TipoCombustivel tipoCombustivel;
}