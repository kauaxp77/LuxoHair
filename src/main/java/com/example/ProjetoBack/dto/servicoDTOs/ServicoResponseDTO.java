package com.example.ProjetoBack.dto.servicoDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServicoResponseDTO {
    private Long id_servico;

    private String nome_servico;

    private LocalTime duracao_estimada;

    private BigDecimal preco;
}
