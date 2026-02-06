package com.example.apiambienteescolarsql.repository;

import com.example.apiambienteescolarsql.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query("SELECT a FROM Aluno a WHERE a.matricula = :matricula")
    Aluno findByMatricula(@Param("matricula") String matricula);

    @Query("SELECT a FROM Aluno a WHERE a.senha = :senha AND a.email = :email")
    Aluno findByEmailAndSenha(@Param("email") String email, @Param("senha") String senha);

    @Query("SELECT a FROM Aluno a WHERE a.status = :status")
    List<Aluno> findByStatus(@Param("status") String status);
}