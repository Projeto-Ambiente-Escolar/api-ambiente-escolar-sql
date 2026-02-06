package com.example.apiambienteescolarsql.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ncdprofessor")
    private Long id;

    @Column(name = "cnmprofessor", length = 100)
    private String nome;

    @Column(name = "cusuario", length = 50)
    private String usuario;

    @Column(name = "csenha", length = 50)
    private String senha;

    @Column(name = "cdisciplina", length = 50)
    private String disciplina;
}
