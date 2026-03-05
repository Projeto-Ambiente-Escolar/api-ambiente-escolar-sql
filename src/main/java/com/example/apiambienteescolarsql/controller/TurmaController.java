package com.example.apiambienteescolarsql.controller;

import com.example.apiambienteescolarsql.dto.TurmaResponse;
import com.example.apiambienteescolarsql.service.TurmaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/turma")
public class TurmaController {

    private final TurmaService turmaService;

    @GetMapping("/listar")
    private ResponseEntity<List<TurmaResponse>> findAll(){return ResponseEntity.ok(turmaService.findAll());}

    @GetMapping("/media/{professorId}/{turmaId}")
    public ResponseEntity<Double> findMedia(@PathVariable Long professorId, @PathVariable Long turmaId) {
        Double media = turmaService.findMediaTurma(professorId, turmaId);
        return new ResponseEntity<>(media, HttpStatus.OK);
    }
}
