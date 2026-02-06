package com.example.apiambienteescolarsql.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ncdturma")
    private Long id;

    @Column(name = "iserie")
    private Integer serie;

    @Column(name = "cnmturma")
    private String turma;
}
