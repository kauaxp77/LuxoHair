package com.example.ProjetoBack.controller;

import com.example.ProjetoBack.Service.AgendamentoServices;
import com.example.ProjetoBack.dto.agendamentoDOTs.AgendamentoResponseDTO;
import com.example.ProjetoBack.dto.authDTOs.RegisterRequestDTO;
import com.example.ProjetoBack.model.user.User;
import com.example.ProjetoBack.model.user.UserRole;
import com.example.ProjetoBack.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gerente")
public class GerenteController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AgendamentoServices agendamentoServices;

    /** Cadastra novo funcionário com a role enviada no body (FUNC ou ADMIN) */
    @PostMapping("/funcionarios")
    public ResponseEntity register(@RequestBody @Valid RegisterRequestDTO dto) {
        if (this.repository.findByLogin(dto.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        // Usa a role que veio no DTO; se nula, padrão FUNC
        UserRole role = dto.role() != null ? dto.role() : UserRole.FUNC;
        User newUser = new User(dto.login(), encryptedPassword, role);
        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

    /** Lista todos os usuários com role FUNC (profissionais técnicas) */
    @GetMapping("/funcionarios")
    public ResponseEntity<List<Map<String, Object>>> listarFuncionarios() {
        List<Map<String, Object>> funcionarios = repository.findAll()
                .stream()
                .filter(u -> u.getRole() == UserRole.FUNC)
                .map(u -> Map.<String, Object>of(
                        "id",    u.getId(),
                        "login", u.getLogin(),
                        "role",  u.getRole().name()
                ))
                .toList();

        return ResponseEntity.ok(funcionarios);
    }

    /** Lista todos os agendamentos (visão gerente) */
    @GetMapping("/agendamentos")
    public ResponseEntity<List<AgendamentoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(agendamentoServices.listarTodos());
    }
}
