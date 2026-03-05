package com.example.apiambienteescolarsql.controller;

import com.example.apiambienteescolarsql.dto.NotasRequest;
import com.example.apiambienteescolarsql.dto.NotasResponse;
import com.example.apiambienteescolarsql.dto.projection.TabelaNotaResponse;
import com.example.apiambienteescolarsql.dto.projection.RankingAlunoProjection;
import com.example.apiambienteescolarsql.service.NotasService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/notas")
public class NotasController {
    private final NotasService notasService;

    @PostMapping("/inserirNota")
    public ResponseEntity<NotasResponse> inserirNota(
            @Valid @RequestBody NotasRequest requestNotasDTO) {
        NotasResponse responseNotasDTO = notasService.inserirNota(requestNotasDTO);
        return new ResponseEntity<>(responseNotasDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotasResponse> findById(@PathVariable Long id) {
        NotasResponse responseNotasDTO = notasService.findById(id);
        return new ResponseEntity<>(responseNotasDTO, HttpStatus.OK);
    }

    @GetMapping("/media/{idProfessor}")
    public ResponseEntity<Double> findMedia(@PathVariable Long idProfessor) {
        Double media = notasService.findMedia(idProfessor);
        return new ResponseEntity<>(media, HttpStatus.OK);
    }
    @GetMapping("/tabelaNotas/{idAluno}")
    public ResponseEntity<List<TabelaNotaResponse>> listarNotas(@PathVariable Long idAluno) {
        return ResponseEntity.ok(notasService.buscarTabelaNotas(idAluno));
    }

    @GetMapping("/{aluno}/{professor}")
    public ResponseEntity<List<NotasResponse>> findByAlunoAndProfessor(@PathVariable Long aluno, @PathVariable Long professor ) {
        List<NotasResponse> responseAlunosDTO = notasService.findByAlunoAndProfessor(aluno, professor);
        return new ResponseEntity<>(responseAlunosDTO, HttpStatus.OK);
    }

    @GetMapping("/buscarTop3Alunos/{professor}")
    public ResponseEntity<List<RankingAlunoProjection>> findTop3Alunos(@PathVariable Long professor) {
        List<RankingAlunoProjection> rankingAlunoProjection = notasService.findTop3Alunos(professor);
        return new ResponseEntity<>(rankingAlunoProjection, HttpStatus.OK);
    }

    @GetMapping("/buscarAlunosEmRecuperacao/{professor}")
    public ResponseEntity<List<RankingAlunoProjection>> findAlunosRecuperacao(@PathVariable Long professor) {
        List<RankingAlunoProjection> rankingAlunoProjection = notasService.findAlunosRecuperacao(professor);
        return new ResponseEntity<>(rankingAlunoProjection, HttpStatus.OK);
    }
}
