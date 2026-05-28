package com.example.ProjetoBack.dto.clientesDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {
    private Long id_cliente;

    private String nome;

    private String telefone;

    private String email;
}
