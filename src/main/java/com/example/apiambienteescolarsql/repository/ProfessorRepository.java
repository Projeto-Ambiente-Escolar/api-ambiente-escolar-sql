package com.example.apiambienteescolarsql.repository;

import com.example.apiambienteescolarsql.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    @Query("SELECT a FROM Professor a WHERE a.senha = :senha AND a.usuario = :email")
    Professor findByEmailAndSenha(@Param("email") String email, @Param("senha") String senha);
}
