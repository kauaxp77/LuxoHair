package com.example.ProjetoBack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // Permite acesso público à página de login, à URL de processamento do login e aos recursos estáticos
                .requestMatchers("/login", "/perform_login", "/css/**", "/js/**", "/images/**").permitAll()
                // Qualquer outra requisição (incluindo /dashboard) precisa de autenticação
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                // Define a página de login personalizada
                .loginPage("/login")
                // Define a URL para onde o formulário de login será enviado (POST)
                .loginProcessingUrl("/perform_login")
                // Redireciona para /dashboard após o login bem-sucedido
                .defaultSuccessUrl("/dashboard", true)
                // A permissão para a página de login já foi concedida acima
            )
            .logout(logout -> logout
                // Configura a URL de logout e permite acesso a todos
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
            .username("admin@test.com")
            .password(passwordEncoder().encode("password"))
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
