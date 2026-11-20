package com.example.ProjetoBack.repository;

import com.example.ProjetoBack.model.Agendamento;
import com.example.ProjetoBack.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByCliente(User cliente);

    List<Agendamento> findByProfissional(User profissional);
}
