package com.example.apiambienteescolarsql.service;

import com.example.apiambienteescolarsql.dto.TurmaResponse;
import com.example.apiambienteescolarsql.repository.AlunoRepository;
import com.example.apiambienteescolarsql.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TurmaService {
    public final TurmaRepository turmaRepository;
    private final ObjectMapper objectMapper;

    public List<TurmaResponse> findAll(){
        return  turmaRepository.findAll().stream().map(turma -> objectMapper.convertValue(turma, TurmaResponse.class)).toList();
    }

}
