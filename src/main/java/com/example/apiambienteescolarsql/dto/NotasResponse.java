package com.example.apiambienteescolarsql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotasResponse {
    private Long aluno;

    private Long professor;

    private Double nota1;

    private Double nota2;

    private Double media;

    private String observacao;
}
