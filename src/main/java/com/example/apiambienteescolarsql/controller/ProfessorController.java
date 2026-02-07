package com.example.apiambienteescolarsql.controller;

import com.example.apiambienteescolarsql.dto.LoginRequest;
import com.example.apiambienteescolarsql.dto.ProfessorResponse;
import com.example.apiambienteescolarsql.service.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/professor")
public class ProfessorController {
    private final ProfessorService professorService;

    @PostMapping("/login")
    public ResponseEntity<ProfessorResponse> loginProfessor(@RequestBody LoginRequest loginRequest) {
        LoginRequest requestAlunoDTO = new LoginRequest(loginRequest.getEmail(), loginRequest.getSenha());
        ProfessorResponse responseAlunoDTO = professorService.findByEmailAndSenha(requestAlunoDTO);
        return new ResponseEntity<>(responseAlunoDTO, HttpStatus.OK);
    }
}
