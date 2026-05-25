package com.example.ProjetoBack.dto.agendamentoDOTs;

import com.example.ProjetoBack.model.Cliente;
import com.example.ProjetoBack.model.Profissional;
import com.example.ProjetoBack.model.Servico;
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

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_profissional")
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "id_servico")
    private Servico servico;

    private LocalDateTime data_hora;

    private String status;
}
