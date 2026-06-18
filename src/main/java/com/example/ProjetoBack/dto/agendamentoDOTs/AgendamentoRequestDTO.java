package com.example.ProjetoBack.dto.agendamentoDOTs;

import com.example.ProjetoBack.model.Servico;
import com.example.ProjetoBack.model.user.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgendamentoRequestDTO {


    private String profissionalLogin;

    private Long servicoId;

    private LocalDateTime data_hora;

    private String status;
}
