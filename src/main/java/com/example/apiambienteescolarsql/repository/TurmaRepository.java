package com.example.apiambienteescolarsql.repository;

import com.example.apiambienteescolarsql.dto.projection.TurmaMediaProjection;
import com.example.apiambienteescolarsql.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    @Query(
            value = """
                 SELECT t.ncdturma, t.iserie, t.cnmturma, ROUND(AVG(n.nMedia),2) AS nMedia
                 FROM Notas n
                 JOIN Aluno a ON n.nCdAluno = a.nCdAluno
                 JOIN Turma t ON a.nCdTurma = t.nCdTurma
                 WHERE n.nCdProfessor = :idProfessor
                 AND t.nCdTurma = :idTurma
                 GROUP BY t.ncdturma, t.iserie, t.cnmturma
            """,
            nativeQuery = true
    )
    TurmaMediaProjection findMediaTurma(@Param("idProfessor") Long idProfessor, @Param("idTurma") Long idTurma);
}
