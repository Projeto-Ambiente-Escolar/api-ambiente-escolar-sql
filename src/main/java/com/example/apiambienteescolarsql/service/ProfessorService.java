package com.example.apiambienteescolarsql.service;

import com.example.apiambienteescolarsql.dto.LoginRequest;
import com.example.apiambienteescolarsql.dto.ProfessorResponse;
import com.example.apiambienteescolarsql.model.Professor;
import com.example.apiambienteescolarsql.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    public final ProfessorRepository professorRepository;
    private final ObjectMapper objectMapper;

    // get by email e senha
    public ProfessorResponse findByEmailAndSenha(LoginRequest loginRequest) {
        Professor professor = professorRepository.findByEmailAndSenha(
                loginRequest.getEmail(),
                loginRequest.getSenha()
        );
        return objectMapper.convertValue(professor, ProfessorResponse.class);
    }

}
