package com.example.ProjetoBack.dto.clientesDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    @Pattern(regexp = "\\d{10,11}")
    private String telefone;

    @Email
    @NotBlank
    private String email;

}
