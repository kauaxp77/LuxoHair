package com.example.ProjetoBack.model.user;

public enum UserRole {
    ADMIN("admin"),
<<<<<<< HEAD
    FUNC("funcionari"),
=======
>>>>>>> 920d891ebec45e5918a1e1175123614d2b6218a7
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
