package com.example.ProjetoBack.repository;

import com.example.ProjetoBack.model.user.User;
import com.example.ProjetoBack.model.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);

    boolean existsByRole(UserRole role);
}
