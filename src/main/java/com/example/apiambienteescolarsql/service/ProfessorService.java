package com.example.apiambienteescolarsql.service;

import com.example.apiambienteescolarsql.dto.AlunoResponse;
import com.example.apiambienteescolarsql.dto.LoginRequest;
import com.example.apiambienteescolarsql.dto.ProfessorRequest;
import com.example.apiambienteescolarsql.dto.ProfessorResponse;
import com.example.apiambienteescolarsql.exception.DatabaseInsertException;
import com.example.apiambienteescolarsql.exception.DuplicateException;
import com.example.apiambienteescolarsql.model.Aluno;
import com.example.apiambienteescolarsql.model.Professor;
import com.example.apiambienteescolarsql.repository.ProfessorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    public final ProfessorRepository professorRepository;
    private final ObjectMapper objectMapper;

    //get by id do professor
    public ProfessorResponse findById(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor com ID " + id + " não encontrado."));
        return objectMapper.convertValue(professor, ProfessorResponse.class);
    }

    public List<ProfessorResponse> findAll(){
        return  professorRepository.findAll().stream().map(professor -> objectMapper.convertValue(professor, ProfessorResponse.class)).toList();
    }

    public List<String> findAllDisciplina(){
        return professorRepository.findAllDisciplina();
    }
    
    // get by email e senha
    public ProfessorResponse findByEmailAndSenha(LoginRequest loginRequest) {
        Professor professor = professorRepository.findByEmailAndSenha(
                loginRequest.getEmail(),
                loginRequest.getSenha()
        );
        return objectMapper.convertValue(professor, ProfessorResponse.class);
    }


    public ProfessorResponse criarProfessor (ProfessorRequest professorRequest) {
        try{
        Professor professor = objectMapper.convertValue(professorRequest, Professor.class);
        return objectMapper.convertValue(professorRepository.save(professor), ProfessorResponse.class);
        } catch (DataAccessException dataAccessException) {
            String mensagem = dataAccessException.getMostSpecificCause().getMessage();
            String mensagemErroConflito = "Falha na operação: Já existe um professor cadastrado";
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
}
