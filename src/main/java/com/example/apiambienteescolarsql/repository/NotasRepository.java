package com.example.apiambienteescolarsql.repository;

import com.example.apiambienteescolarsql.dto.TabelaNotaResponse;
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

    @Query(
            value = "SELECT ROUND(AVG(a.nMedia), 2) FROM notas a",
            nativeQuery = true
    )
    Double findMedia();

    @Query("SELECT a FROM Notas a WHERE a.aluno = :aluno AND a.professor = :professor")
    List<Notas> findByAlunoAndProfessor(@Param("aluno") Long aluno, @Param("professor") Long professor);

    @Query(value = """
        SELECT 
            a.cFoto       AS cFoto,
            a.cNmAluno    AS cNmAluno,
            t.nCdTurma    AS nCdTurma,
            a.cMatricula  AS cMatricula,
            a.cEmail      AS cEmail,
            n.nMedia      AS nMedia
        FROM Turma t
        INNER JOIN Aluno a ON t.nCdTurma = a.nCdTurma
        INNER JOIN Notas n ON a.nCdAluno = n.nCdAluno
        INNER JOIN Professor p ON p.nCdProfessor = n.nCdProfessor
        WHERE p.nCdProfessor = :professor
        ORDER BY n.nMedia DESC
        LIMIT 3
        """,
            nativeQuery = true)
    List<RankingAlunoProjection> findTop3AlunosByProfessor(@Param("professor") Long professor);

    @Query(value = """

            SELECT\s
            a.cFoto       AS cFoto,
            a.cNmAluno    AS cNmAluno,
            t.nCdTurma    AS nCdTurma,
            a.cMatricula  AS cMatricula,
            a.cEmail      AS cEmail,
            n.nMedia      AS nMedia
        FROM Turma t
        INNER JOIN Aluno a ON t.nCdTurma = a.nCdTurma
        INNER JOIN Notas n ON a.nCdAluno = n.nCdAluno
        INNER JOIN Professor p ON p.nCdProfessor = n.nCdProfessor
        WHERE p.nCdProfessor = :professor
          AND n.nMedia < 7
        ORDER BY n.nMedia ASC;
        """,
            nativeQuery = true)
    List<RankingAlunoProjection> findAlunosRecuperacaoByProfessor(@Param("professor") Long professor);

    @Query(value = """
    SELECT 
        Notas.nNota1       AS nota1,
        Notas.nNota2       AS nota2,
        Notas.nMedia       AS media,
        Notas.cObservacao  AS observacao,
        Professor.cDisciplina AS disciplina
    FROM Notas
    INNER JOIN Professor 
        ON Professor.nCdProfessor = Notas.nCdProfessor
    WHERE Notas.nCdAluno = :nCdAluno
    """, nativeQuery = true)
    List<TabelaNotaResponse> findNotasByAluno(@Param("nCdAluno") Long nCdAluno);
}
