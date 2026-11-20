package com.example.ProjetoBack.infra.security;

import com.example.ProjetoBack.model.user.User;
import com.example.ProjetoBack.model.user.UserRole;
import com.example.ProjetoBack.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Cria o primeiro gerente (ADMIN) na inicialização.
 *
 * Como o cadastro público só cria clientes, o primeiro gerente vem das variáveis de ambiente
 * ADMIN_LOGIN e ADMIN_PASSWORD. Ele só é criado se ainda não existir nenhum ADMIN no banco;
 * depois disso, os demais funcionários e gerentes são cadastrados pelo próprio gerente.
 */
@Component
public class AdminInicial implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AdminInicial.class);

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final String login;
    private final String senha;

    public AdminInicial(UserRepository repository,
                        PasswordEncoder passwordEncoder,
                        @Value("${app.admin.login:}") String login,
                        @Value("${app.admin.password:}") String senha) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.login = login;
        this.senha = senha;
    }

    @Override
    public void run(String... args) {
        if (repository.existsByRole(UserRole.ADMIN)) {
            return;
        }
        if (login.isBlank() || senha.isBlank()) {
            log.warn("Nenhum gerente (ADMIN) cadastrado. Defina ADMIN_LOGIN e ADMIN_PASSWORD para criar o primeiro.");
            return;
        }
        if (repository.findByLogin(login) != null) {
            log.warn("O login {} já existe e não é gerente; o gerente inicial não foi criado.", login);
            return;
        }
        repository.save(new User(login, passwordEncoder.encode(senha), UserRole.ADMIN));
        log.info("Gerente inicial {} criado.", login);
    }
}
