package com.example.ProjetoBack.dto.agendamentoDOTs;

<<<<<<< HEAD
import com.example.ProjetoBack.model.Servico;
import com.example.ProjetoBack.model.user.User;
=======
import com.example.ProjetoBack.model.Cliente;
import com.example.ProjetoBack.model.Profissional;
import com.example.ProjetoBack.model.Servico;
>>>>>>> 920d891ebec45e5918a1e1175123614d2b6218a7
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

<<<<<<< HEAD
    private String cliente;

    private String profissional;

    private String servico;
=======
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_profissional")
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "id_servico")
    private Servico servico;
>>>>>>> 920d891ebec45e5918a1e1175123614d2b6218a7

    private LocalDateTime data_hora;

    private String status;
<<<<<<< HEAD

=======
>>>>>>> 920d891ebec45e5918a1e1175123614d2b6218a7
}
