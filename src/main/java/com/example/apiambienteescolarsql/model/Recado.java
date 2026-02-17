package com.example.apiambienteescolarsql.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Recado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ncdrecado")
    private Long id;

    @Column(name = "ncdprofessor")
    private Long professor;

    @Column(name = "ncdaluno")
    private Long aluno;

    @Column(name = "cmensagem", length = 500)
    private String mensagem;
}
