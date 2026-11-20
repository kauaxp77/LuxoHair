package com.example.ProjetoBack.controller;

import com.example.ProjetoBack.Service.AgendamentoServices;
import com.example.ProjetoBack.dto.agendamentoDOTs.AgendamentoRequestDTO;
import com.example.ProjetoBack.dto.agendamentoDOTs.AgendamentoResponseDTO;
import com.example.ProjetoBack.dto.authDTOs.RegisterRequestDTO;
import com.example.ProjetoBack.model.user.User;
import com.example.ProjetoBack.model.user.UserRole;
import com.example.ProjetoBack.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AgendamentoServices agendamentoServices;

    /** Cliente faz o agendamento já logado */
    @PostMapping("/agendamento")
    ResponseEntity<AgendamentoResponseDTO> adicionarAgendamento(@Valid @RequestBody AgendamentoRequestDTO dto) {
        User cliente = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        AgendamentoResponseDTO responseDTO = agendamentoServices.adicionarAgendamento(dto, cliente);
        return ResponseEntity.ok(responseDTO);
    }

    /** Lista os agendamentos do cliente logado */
    @GetMapping("/meus-agendamentos")
    public ResponseEntity<List<AgendamentoResponseDTO>> listar() {
        return ResponseEntity.ok(agendamentoServices.listarPorCliente());
    }

    /**
     * Lista as profissionais técnicas (role FUNC) para o cliente poder
     * selecionar no formulário de agendamento.
     * Acessível por qualquer usuário autenticado.
     */
    @GetMapping("/profissionais")
    public ResponseEntity<List<Map<String, Object>>> listarProfissionais() {
        List<Map<String, Object>> profissionais = repository.findAll()
                .stream()
                .filter(u -> u.getRole() == UserRole.FUNC)
                .map(u -> Map.<String, Object>of(
                        "id",    u.getId(),
                        "login", u.getLogin()
                ))
                .toList();

        return ResponseEntity.ok(profissionais);
    }

    /** Cliente se registra na tela de login */
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequestDTO dto) {
        if (this.repository.findByLogin(dto.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User newUser = new User(dto.login(), encryptedPassword, UserRole.USER);
        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
