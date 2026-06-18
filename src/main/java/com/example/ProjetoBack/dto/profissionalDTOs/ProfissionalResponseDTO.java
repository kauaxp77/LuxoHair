package com.example.ProjetoBack.dto.profissionalDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfissionalResponseDTO {
    private Long id_profissional;

    private String nome;

    private String especialidade;
}
