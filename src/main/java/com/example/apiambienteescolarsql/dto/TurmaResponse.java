package com.example.apiambienteescolarsql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaResponse {

    private Long id;

    private Integer serie;

    private String turma;
}
