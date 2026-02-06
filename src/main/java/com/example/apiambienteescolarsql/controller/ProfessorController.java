package com.example.apiambienteescolarsql.controller;

import com.example.apiambienteescolarsql.dto.LoginRequest;
import com.example.apiambienteescolarsql.dto.ProfessorResponse;
import com.example.apiambienteescolarsql.service.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/professor")
public class ProfessorController {
    private final ProfessorService professorService;

    @GetMapping("/login")
    public ResponseEntity<ProfessorResponse> loginProfessor(
            @RequestParam String email,
            @RequestParam String senha) {
        LoginRequest requestAlunoDTO = new LoginRequest(email, senha);
        ProfessorResponse responseAlunoDTO = professorService.findByEmailAndSenha(requestAlunoDTO);
        return new ResponseEntity<>(responseAlunoDTO, HttpStatus.OK);
    }
}
