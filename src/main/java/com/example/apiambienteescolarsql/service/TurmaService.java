package com.example.apiambienteescolarsql.service;

import com.example.apiambienteescolarsql.dto.TurmaResponse;
import com.example.apiambienteescolarsql.repository.TurmaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
