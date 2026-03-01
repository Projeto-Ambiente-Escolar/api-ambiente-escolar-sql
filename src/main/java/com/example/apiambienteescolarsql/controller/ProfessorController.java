package com.example.apiambienteescolarsql.controller;

import com.example.apiambienteescolarsql.dto.ProfessorResponse;
import com.example.apiambienteescolarsql.dto.LoginRequest;
import com.example.apiambienteescolarsql.service.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/professor")
public class ProfessorController {
    private final ProfessorService professorService;

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponse> findById(@PathVariable Long id) {
        ProfessorResponse responseProfessorDTO = professorService.findById(id);
        return new ResponseEntity<>(responseProfessorDTO, HttpStatus.OK);
    }

    @GetMapping("/listar")
    private ResponseEntity<List<ProfessorResponse>> findAll(){return ResponseEntity.ok(professorService.findAll());}

    @GetMapping("/listarDisciplinas")
    private ResponseEntity<List<String>> findDisciplinas(){return ResponseEntity.ok(professorService.findAllDisciplina());}

    @PostMapping("/login")
    public ResponseEntity<ProfessorResponse> loginProfessor(@RequestBody LoginRequest loginRequest) {
        LoginRequest requestProfessorDTO = new LoginRequest(loginRequest.getEmail(), loginRequest.getSenha());
        ProfessorResponse responseProfessorDTO = professorService.findByEmailAndSenha(requestProfessorDTO);
        return new ResponseEntity<>(responseProfessorDTO, HttpStatus.OK);
    }
}
