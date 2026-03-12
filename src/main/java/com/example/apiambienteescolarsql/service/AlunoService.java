package com.example.apiambienteescolarsql.service;

import com.example.apiambienteescolarsql.dto.*;
import com.example.apiambienteescolarsql.dto.projection.MateriaStatusProjection;
import com.example.apiambienteescolarsql.dto.projection.NotasAlunoProjection;
import com.example.apiambienteescolarsql.exception.DatabaseInsertException;
import com.example.apiambienteescolarsql.exception.DuplicateException;
import com.example.apiambienteescolarsql.model.Aluno;
import com.example.apiambienteescolarsql.repository.AlunoRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {
    public final AlunoRepository alunoRepository;
    private final ObjectMapper objectMapper;

    //get by id do aluno
    public AlunoResponse findById(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno com ID " + id + " não encontrado."));
        return objectMapper.convertValue(aluno, AlunoResponse.class);
    }

    // get by id do aluno e do professor
    public NotasAlunoProjection buscarNotasAluno(Long nCdAluno, Long nCdProfessor){
        return alunoRepository.buscarNotasAluno(nCdAluno, nCdProfessor);
    }

    // get by email e senha
    public AlunoResponse findByEmailAndSenha(LoginRequest loginRequest) {
        Aluno aluno = alunoRepository.findByEmailAndSenha(
                loginRequest.getEmail(),
                loginRequest.getSenha()
        );
        return objectMapper.convertValue(aluno, AlunoResponse.class);
    }

    public List<AlunoResponse> listarAlunosPorTurma(Long idTurma) {
        return alunoRepository.findAlunosByTurma(idTurma)
                .stream()
                .map(aluno -> objectMapper.convertValue(aluno, AlunoResponse.class))
                .toList();
    }

    public List<MateriaStatusProjection> listarAlunosComStatusDaMateria(Long professorId, Long turmaId) {
        return alunoRepository.buscarAlunosComStatusDaMateria(professorId, turmaId);
    }

    public MateriaStatusProjection listarAlunoComStatusDaMateria(Long professorId, Long serie, Long alunoId) {
        return alunoRepository.buscarAlunoComStatusDaMateria(professorId, serie, alunoId);
    }

    //get by status
    public List<AlunoResponse> findByStatus(String status) {
        List<Aluno> alunos = alunoRepository.findByStatus(status);
        return objectMapper.convertValue(
                alunos,
                new TypeReference<List<AlunoResponse>>() {}
        );
    }

    // get by matricula
    public AlunoResponse findByMatricula(String matricula) {
        Aluno aluno = alunoRepository.findByMatricula(matricula);
        return objectMapper.convertValue(aluno, AlunoResponse.class);
    }

    //get alunos que ainda não tem nota
    public List<AlunoResponse> listarAlunosSemNota(Long idProfessor, Long idTurma) {
        return  alunoRepository.findAlunosSemNota(idProfessor, idTurma)
                .stream()
                .map(aluno -> objectMapper.convertValue(aluno, AlunoResponse.class))
                .toList();
    }

    //get all
    public List<AlunoResponse> listarAlunos() {
        return alunoRepository.findAll()
                .stream()
                .map(aluno -> objectMapper.convertValue(aluno, AlunoResponse.class))
                .toList();
    }

    //cadastra o aluno
    @Transactional
    public AlunoResponse cadastrarAluno(AlunoRequest requestAlunoDTO) {
        try {
            Aluno aluno = objectMapper.convertValue(requestAlunoDTO, Aluno.class);
            return objectMapper.convertValue(alunoRepository.save(aluno), AlunoResponse.class);

        } catch (DataAccessException dataAccessException) {
            String mensagem = dataAccessException.getMostSpecificCause().getMessage();
            String mensagemErroConflito = "Falha na operação: Já existe um aluno cadastrado";
            String mensagemErroInserir =
                    "Falha ao inserir, reconsidere visualizar seus parametros e atributos";

            if (mensagem != null && mensagem.contains(mensagemErroConflito)) {
                throw new DuplicateException(mensagemErroConflito);
            }
            if (mensagem != null && mensagem.contains(mensagemErroInserir)) {
                throw new DatabaseInsertException(mensagemErroInserir);
            }
            throw dataAccessException;
        }
    }

    @Transactional
    public AlunoResponse mudarStatusAluno(Long id, String status) {

        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno com a matrícula " + id + " não foi encontrado."));

        if("0".equals(status)){
            alunoRepository.delete(aluno);
            return null;
        }

        aluno.setStatus(status);
        return objectMapper.convertValue(
                alunoRepository.save(aluno),
                AlunoResponse.class
        );
    }

    @Transactional
    public AlunoResponse vincularTurma(Long idAluno, Long idTurma)
    {
        Aluno aluno = alunoRepository.findById(idAluno).orElseThrow(() -> new EntityNotFoundException("Aluno com a matrícula " + idAluno + " não foi encontrado."));
        aluno.setTurma(idTurma);
        return objectMapper.convertValue(
                alunoRepository.save(aluno),
                AlunoResponse.class
        );
    }


}
