package com.example.apiambienteescolarsql.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
