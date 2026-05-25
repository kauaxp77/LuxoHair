package com.example.ProjetoBack.dto.authDTOs;

import com.example.ProjetoBack.model.user.UserRole;

public record RegisterRequestDTO(String login, String password, UserRole role) {
}
