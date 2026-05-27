package com.example.ProjetoBack.controller;

import com.example.ProjetoBack.dto.authDTOs.LoginRequestDTO;
import com.example.ProjetoBack.dto.authDTOs.LoginResponseDTO;
import com.example.ProjetoBack.dto.authDTOs.RegisterRequestDTO;
import com.example.ProjetoBack.infra.security.TokenService;
import com.example.ProjetoBack.model.user.User;
import com.example.ProjetoBack.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequestDTO dto){
        var usernamePassWord = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassWord);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequestDTO dto){
        if(this.repository.findByLogin(dto.login()) != null){
            return ResponseEntity.badRequest().build();
        }

        String encrpytedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User newUser = new User(dto.login(), encrpytedPassword, dto.role());
        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/dashboard")
    public String getDashboard() {
        // Esta string "dashboard" diz ao Spring para renderizar o arquivo "dashboard.html"
        return "dashboard";
    }

    @GetMapping("/login")
    public String login() {
        // Retorna o nome do arquivo HTML (login.html) da pasta templates
        return "login";
    }
}
