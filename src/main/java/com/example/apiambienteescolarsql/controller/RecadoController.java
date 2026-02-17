package com.example.apiambienteescolarsql.controller;

import com.example.apiambienteescolarsql.dto.RecadoRequest;
import com.example.apiambienteescolarsql.model.Recado;
import com.example.apiambienteescolarsql.service.RecadoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/recado")
public class RecadoController {
    private final RecadoService recadoService;

    @GetMapping("/listarPorAluno/{aluno}")
    private ResponseEntity<List<Recado>> findByAluno(@PathVariable Long aluno){
        return ResponseEntity.ok(recadoService.findByAluno(aluno));
    }

    @GetMapping("/listarPorProfessor/{professor}")
    private ResponseEntity<List<Recado>> findByProfessor(@PathVariable Long professor){
        return ResponseEntity.ok(recadoService.findByProfessor(professor));
    }

    @PostMapping("/criarRecado")
    public ResponseEntity<Recado> loginRecado(@RequestBody RecadoRequest recadoRequest) {
        Recado responseRecadoDTO = recadoService.inserirRecado(recadoRequest);
        return new ResponseEntity<>(responseRecadoDTO, HttpStatus.OK);
    }

}
