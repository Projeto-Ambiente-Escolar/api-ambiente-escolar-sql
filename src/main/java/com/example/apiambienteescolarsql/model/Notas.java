package com.example.apiambienteescolarsql.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Notas {
    @Id
    @Column(name = "ncdaluno")
    private Long aluno;

    @Column(name = "cobservacao")
    private String observacao;

    @Column(name = "ncdprofessor")
    private Long professor;

    @Column(name = "nnota1", precision = 5, scale = 2)
    private Long nota1;

    @Column(name = "nnota2", precision = 5, scale = 2)
    private Long nota2;

    @Column(name = "nmedia", precision = 5, scale = 2)
    private Long media;
}
