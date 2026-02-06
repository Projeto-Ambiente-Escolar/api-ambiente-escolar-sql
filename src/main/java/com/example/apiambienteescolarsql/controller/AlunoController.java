package com.example.apiambienteescolarsql.controller;

import com.example.apiambienteescolarsql.dto.AlunoRequest;
import com.example.apiambienteescolarsql.dto.AlunoResponse;
import com.example.apiambienteescolarsql.dto.LoginRequest;
import com.example.apiambienteescolarsql.service.AlunoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/aluno")
public class AlunoController {
    private final AlunoService alunoService;

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> findById(@PathVariable Long id) {
        AlunoResponse responseAlunoDTO = alunoService.findById(id);
        return new ResponseEntity<>(responseAlunoDTO, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<AlunoResponse> loginAluno(
            @RequestParam String email,
            @RequestParam String senha) {
        LoginRequest requestAlunoDTO = new LoginRequest(email, senha);
        AlunoResponse responseAlunoDTO = alunoService.findByEmailAndSenha(requestAlunoDTO);
        return new ResponseEntity<>(responseAlunoDTO, HttpStatus.OK);
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<AlunoResponse>> findByStatus(@PathVariable String status) {
        List<AlunoResponse> responseAlunosDTO = alunoService.findByStatus(status);
        return new ResponseEntity<>(responseAlunosDTO, HttpStatus.OK);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<AlunoResponse> findByMatricula(@PathVariable String matricula) {
        AlunoResponse responseAlunosDTO = alunoService.findByMatricula(matricula);
        return new ResponseEntity<>(responseAlunosDTO, HttpStatus.OK);
    }

    @GetMapping("/selecionarTodos")
    public ResponseEntity<List<AlunoResponse>> listarAlunos() {
        return ResponseEntity.ok(alunoService.listarAlunos());
    }

    @PostMapping("/cadastrarAluno")
    public ResponseEntity<AlunoResponse> cadastrarAluno(
            @Valid @RequestBody AlunoRequest requestAlunoDTO) {
        AlunoResponse responseAlunoDTO = alunoService.cadastrarAluno(requestAlunoDTO);
        return new ResponseEntity<>(responseAlunoDTO, HttpStatus.CREATED);
    }

    @PutMapping("/alterarStatus/{id}/{status}")
    public ResponseEntity<AlunoResponse> mudarStatusAluno(@PathVariable Long id, @PathVariable String status) {
        AlunoResponse responseAlunoDTO = alunoService.mudarStatusAluno(id, status);
        return ResponseEntity.ok(responseAlunoDTO);
    }
}
