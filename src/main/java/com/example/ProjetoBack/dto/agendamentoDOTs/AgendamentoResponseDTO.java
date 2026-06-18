package com.example.ProjetoBack.dto.agendamentoDOTs;

import com.example.ProjetoBack.model.Servico;
import com.example.ProjetoBack.model.user.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoResponseDTO {
    private Long id_agendamento;

    private String cliente;

    private String profissional;

    private String servico;

    private LocalDateTime data_hora;

    private String status;

}
