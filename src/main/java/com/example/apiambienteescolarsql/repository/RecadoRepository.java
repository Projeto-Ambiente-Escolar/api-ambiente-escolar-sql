package com.example.apiambienteescolarsql.repository;

import com.example.apiambienteescolarsql.model.Recado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecadoRepository extends JpaRepository<Recado, Long> {
    @Query("SELECT a FROM Recado a WHERE a.aluno = :aluno")
    List<Recado> findByAluno(@Param("aluno") Long aluno);

    @Query("SELECT a FROM Recado a WHERE a.professor = :professor")
    List<Recado> findByProfessor(@Param("professor") Long professor);
}
