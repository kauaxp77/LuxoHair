package com.example.ProjetoBack.model;

import com.example.ProjetoBack.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Data
@Entity

public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_agendamento;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private User cliente;

    @ManyToOne
    @JoinColumn(name = "id_profissional")
    private User profissional;

    @ManyToOne
    @JoinColumn(name = "id_servico")
    private Servico servico;

    private LocalDateTime data_hora;

    private String status;
}
