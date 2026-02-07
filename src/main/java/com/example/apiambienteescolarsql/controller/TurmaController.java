package com.example.apiambienteescolarsql.controller;

import com.example.apiambienteescolarsql.dto.TurmaResponse;
import com.example.apiambienteescolarsql.service.TurmaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/turma")
public class TurmaController {

    private final TurmaService turmaService;

    private ResponseEntity<List<TurmaResponse>> findAll(){return ResponseEntity.ok(turmaService.findAll());}
}
