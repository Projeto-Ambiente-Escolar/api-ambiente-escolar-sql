package com.example.apiambienteescolarsql.service;

import com.example.apiambienteescolarsql.dto.*;
import com.example.apiambienteescolarsql.dto.NotasRequest;
import com.example.apiambienteescolarsql.exception.DatabaseInsertException;
import com.example.apiambienteescolarsql.model.Notas;
import com.example.apiambienteescolarsql.repository.NotasRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotasService {
    private final NotasRepository notasRepository;
    private final ObjectMapper objectMapper;

    // inserir nota
    @Transactional
    public NotasResponse inserirNota(NotasRequest requestNotasDTO) {
        try {
            Notas notas = objectMapper.convertValue(requestNotasDTO, Notas.class);
            return objectMapper.convertValue(notasRepository.save(notas), NotasResponse.class);

        } catch (DataAccessException dataAccessException) {
            String mensagem = dataAccessException.getMostSpecificCause().getMessage();
            String mensagemErroInserir =
                    "Falha ao inserir, reconsidere visualizar seus parâmetros e atributos";
            if (mensagem != null && mensagem.contains(mensagemErroInserir)) {
                throw new DatabaseInsertException(mensagemErroInserir);
            }
            throw dataAccessException;
        }
    }

    // get nota by id aluno
    public NotasResponse findById(Long id) {
        Notas notas = notasRepository.findByAluno(id);
        return objectMapper.convertValue(notas, NotasResponse.class);
    }

    // get notas by aluno e professor
    public List<NotasResponse>  findByAlunoAndProfessor(Long aluno, Long professor) {
        List<Notas> notas = notasRepository.findByAlunoAndProfessor(aluno, professor);
        return objectMapper.convertValue(
                notas,
                new TypeReference<List<NotasResponse>>() {
                }
        );
    }
}
