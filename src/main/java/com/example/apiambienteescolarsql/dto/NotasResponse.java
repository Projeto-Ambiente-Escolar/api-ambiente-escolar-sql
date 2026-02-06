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

    private Long nota1;

    private Long nota2;

    private Long media;
}
