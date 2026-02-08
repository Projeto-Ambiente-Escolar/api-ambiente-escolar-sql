package com.example.apiambienteescolarsql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoResponse {
    private Long id;

    private Long turma;

    private String matricula;

    private String nome;

    private String email;

    private String status;

    private String foto;
}
