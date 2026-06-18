package com.example.ProjetoBack.dto.servicoDTOs;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Getter
@Setter
public class ServicoRequestDTO {

    @NotBlank
    private String nome_servico;

    @NotNull
    private LocalTime duracao_estimada;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal preco;
}
