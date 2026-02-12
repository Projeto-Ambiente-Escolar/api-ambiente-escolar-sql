package com.example.apiambienteescolarsql.repository;

import com.example.apiambienteescolarsql.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    @Query(
            value = "SELECT Turma.iSerie\n" +
                    "     , Turma.cNmTurma\n" +
                    "\t , ROUND(AVG(Notas.nMedia),2)\n" +
                    "  FROM Notas \n" +
                    "       INNER JOIN Aluno ON Aluno.nCdAluno = Notas.nCdAluno \n" +
                    "\t   INNER JOIN Turma ON Turma.nCdTurma = Aluno.nCdTurma\n" +
                    " GROUP BY Turma.iSerie, Turma.cNmTurma",
            nativeQuery = true
    )
    Double findMediaTurma();
}
