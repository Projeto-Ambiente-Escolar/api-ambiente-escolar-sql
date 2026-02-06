package com.example.apiambienteescolarsql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorResponse {
    private Long id;

    private String nome;

    private String usuario;

    private String disciplina;
}
