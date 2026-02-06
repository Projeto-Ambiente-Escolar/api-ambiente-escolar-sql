package com.example.apiambienteescolarsql.repository;

import com.example.apiambienteescolarsql.model.Notas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotasRepository extends JpaRepository<Notas, Long> {
    @Query("SELECT a FROM Notas a WHERE a.aluno = :aluno")
    Notas findByAluno(@Param("aluno") Long aluno);

    @Query("SELECT a FROM Notas a WHERE a.aluno = :aluno AND a.professor = :professor")
    List<Notas> findByAlunoAndProfessor(@Param("aluno") Long aluno, @Param("professor") Long professor);
}
