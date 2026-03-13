package com.example.apiambienteescolarsql.dto;

import jakarta.validation.constraints.NotNull;

public class ProfessorRequest {
    @NotNull
    private String nome;

    @NotNull
    private String usuario;

    @NotNull
    private String senha;

    @NotNull
    private String disciplina;
}
