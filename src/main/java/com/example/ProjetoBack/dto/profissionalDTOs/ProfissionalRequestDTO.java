package com.example.ProjetoBack.dto.profissionalDTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfissionalRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String especialidade;
}
