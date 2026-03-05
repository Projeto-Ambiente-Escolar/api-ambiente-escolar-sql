package com.example.apiambienteescolarsql.repository;

import com.example.apiambienteescolarsql.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    @Query(
            value = """
            SELECT ROUND(AVG(n.nMedia),2)
            	FROM Notas n
            	JOIN Aluno a ON n.nCdAluno = a.nCdAluno
            	JOIN Turma t ON a.nCdTurma = t.nCdTurma
            WHERE n.nCdProfessor = :idProfessor AND t.nCdTurma = :idTurma
            """,
            nativeQuery = true
    )
    Double findMediaTurma(@Param("idProfessor") Long idProfessor, @Param("idTurma") Long idTurma);
}
