package com.example.apiambienteescolarsql.repository;

import com.example.apiambienteescolarsql.dto.projection.MateriaStatusProjection;
import com.example.apiambienteescolarsql.dto.projection.NotasAlunoProjection;
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

    @Query("SELECT a FROM Aluno a WHERE a.senha = :senha AND a.email = :email AND a.status = '1' ")
    Aluno findByEmailAndSenha(@Param("email") String email, @Param("senha") String senha);

    @Query("SELECT a FROM Aluno a WHERE a.status = :status")
    List<Aluno> findByStatus(@Param("status") String status);

    List<Aluno> findAlunosByTurma(Long turma);

    @Query(value = " SELECT Aluno.* " +
                   "  FROM Aluno " +
                   "  LEFT JOIN Notas ON Notas.nCdAluno     = Aluno.nCdAluno " +
                   "                 AND Notas.nCdProfessor = :idProfessor " +
                   " WHERE Aluno.nCdTurma = :idTurma " +
                   "   AND Notas.nCdAluno IS NULL " +
                   " ORDER BY Aluno.nCdAluno",
            nativeQuery = true)
    List<Aluno> findAlunosSemNota(@Param("idProfessor") Long idProfessor, @Param("idTurma") Long idTurma);

    @Query(value = """

            SELECT a.nCdAluno      AS nCdAluno,
                   a.cNmAluno      AS cNmAluno,
                   a.cFoto         AS cFoto,
                   a.cMatricula    AS cMatricula,
                   CONCAT(t.iSerie, ' ', t.cNmTurma) AS serie,
                   CASE
                     WHEN n.nMedia IS NULL THEN 'Em espera'
                     WHEN n.nMedia >= 7 THEN 'Aprovado'
                     ELSE 'Reprovado'
                   END AS status
              FROM Aluno a
                   INNER JOIN Turma t ON t.nCdTurma = a.nCdTurma
                    LEFT JOIN Notas n ON a.nCdAluno = n.nCdAluno
                                     AND n.nCdProfessor = :professorId
             WHERE t.nCdTurma = :turmaId
    """, nativeQuery = true)
    List<MateriaStatusProjection> buscarAlunosComStatusDaMateria(
            @Param("professorId") Long professorId,
            @Param("turmaId") Long turmaId
    );

    @Query(value = """
    SELECT 
        a.nCdAluno      AS nCdAluno,
        a.cNmAluno      AS cNmAluno,
        a.cFoto         AS cFoto,
        a.cMatricula    AS cMatricula,
        CONCAT(t.iSerie, ' ', t.cNmTurma) AS serie,
        CASE 
            WHEN n.nMedia IS NULL THEN 'Em espera'
            WHEN n.nMedia >= 7 THEN 'Aprovado'
            ELSE 'Reprovado'
        END AS status
    FROM Turma t
    JOIN Aluno a ON t.nCdTurma = a.nCdTurma
    JOIN Notas n ON a.nCdAluno = n.nCdAluno
    WHERE n.nCdProfessor = :professorId
      AND a.nCdAluno = :alunoId
      AND t.iSerie = :serie
    """, nativeQuery = true)
    MateriaStatusProjection buscarAlunoComStatusDaMateria(
            @Param("professorId") Long professorId,
            @Param("serie") Long serie,
            @Param("alunoId") Long alunoId
    );

    @Query(value = """
    SELECT 
        a.cNmAluno AS cNmAluno,
        a.cMatricula AS cMatricula,
        t.iSerie AS iSerie,
        t.cNmTurma AS cNmTurma,
        n.nNota1 AS nNota1,
        n.nNota2 AS nNota2,
        n.nMedia AS nMedia
    FROM Notas n
        JOIN Aluno a ON n.nCdAluno = a.nCdAluno
        JOIN Turma t ON a.nCdTurma = t.nCdTurma
    WHERE a.nCdAluno = :nCdAluno 
      AND n.nCdProfessor = :nCdProfessor
""", nativeQuery = true)
    NotasAlunoProjection buscarNotasAluno(
            @Param("nCdAluno") Long nCdAluno,
            @Param("nCdProfessor") Long nCdProfessor
    );
}