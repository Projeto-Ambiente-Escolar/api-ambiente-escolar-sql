package com.example.apiambienteescolarsql.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ncdaluno")
    private Long id;

    @Column(name = "ncdturma")
    private Long turma;

    @Column(name = "cmatricula")
    private String matricula;

    @Column(name = "cnmaluno", length = 100)
    private String nome;

    @Column(name = "cemail", length = 50)
    private String email;

    @Column(name = "csenha", length = 50)
    private String senha;

    @Column(name = "cstatus", length = 1)
    private String status;

    @Column(name = "cfoto")
    private String foto;

}
