package com.example.apiambienteescolarsql.service;

import com.example.apiambienteescolarsql.dto.RecadoRequest;
import com.example.apiambienteescolarsql.model.Recado;
import com.example.apiambienteescolarsql.exception.DatabaseInsertException;
import com.example.apiambienteescolarsql.repository.RecadoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecadoService {
    public final RecadoRepository recadoRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public Recado inserirRecado(RecadoRequest requestRecadoDTO) {
        try {
            Recado recado = objectMapper.convertValue(requestRecadoDTO, Recado.class);
            return objectMapper.convertValue(recadoRepository.save(recado), Recado.class);

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

    public List<Recado> findByAluno(Long aluno){
        return recadoRepository.findByAluno(aluno);
    }

    public List<Recado> findByProfessor(Long professor){
        return recadoRepository.findByProfessor(professor);
    }
}
