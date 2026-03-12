package com.example.apiambienteescolarsql.controller;

import com.example.apiambienteescolarsql.dto.AlunoRequest;
import com.example.apiambienteescolarsql.dto.AlunoResponse;
import com.example.apiambienteescolarsql.dto.LoginRequest;
import com.example.apiambienteescolarsql.dto.projection.MateriaStatusProjection;
import com.example.apiambienteescolarsql.dto.projection.NotasAlunoProjection;
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

    @GetMapping("/OnAPI")
    public ResponseEntity<String> OnAPI(){return ResponseEntity.ok("OnAPI");}

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> findById(@PathVariable Long id) {
        AlunoResponse responseAlunoDTO = alunoService.findById(id);
        return new ResponseEntity<>(responseAlunoDTO, HttpStatus.OK);
    }

    @GetMapping("alunosSemNotaPorTurma/{idProfessor}/{idTurma}")
    public ResponseEntity<List<AlunoResponse>> listarAlunosSemNota(@PathVariable Long idProfessor, @PathVariable Long idTurma) {
        return ResponseEntity.ok(alunoService.listarAlunosSemNota(idProfessor, idTurma));
    }

    @GetMapping("/alunosPorTurma/{idTurma}")
    public ResponseEntity<List<AlunoResponse>> listarAlunosPorTurma(@PathVariable Long idTurma) {
        return ResponseEntity.ok(alunoService.listarAlunosPorTurma(idTurma));
    }

    @PostMapping("/login")
    public ResponseEntity<AlunoResponse> loginAluno(@RequestBody LoginRequest loginRequest) {
        LoginRequest requestAlunoDTO = new LoginRequest(loginRequest.getEmail(), loginRequest.getSenha());
        AlunoResponse responseAlunoDTO = alunoService.findByEmailAndSenha(requestAlunoDTO);
        return new ResponseEntity<>(responseAlunoDTO, HttpStatus.OK);
    }

    @GetMapping("status/{status}")
    public ResponseEntity<List<AlunoResponse>> findByStatus(@PathVariable String status) {
        List<AlunoResponse> responseAlunosDTO = alunoService.findByStatus(status);
        return new ResponseEntity<>(responseAlunosDTO, HttpStatus.OK);
    }

    @GetMapping("matricula/{matricula}")
    public ResponseEntity<AlunoResponse> findByMatricula(@PathVariable String matricula) {
        AlunoResponse responseAlunosDTO = alunoService.findByMatricula(matricula);
        return new ResponseEntity<>(responseAlunosDTO, HttpStatus.OK);
    }

    @GetMapping("/buscarNotasAluno/{idAluno}/{idProfessor}")
    public ResponseEntity<NotasAlunoProjection> listarNotasAluno(Long idAluno, Long idProfessor) {
        return ResponseEntity.ok(alunoService.buscarNotasAluno(idAluno, idProfessor));
    }

    @GetMapping("/selecionarTodos")
    public ResponseEntity<List<AlunoResponse>> listarNotasAluno() {
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
    @PutMapping("/vincularTurma/{idAluno}/{idTurma}")
    public ResponseEntity<AlunoResponse> vincularTurma(@PathVariable Long idAluno, @PathVariable Long idTurma){
        AlunoResponse alunoResponse = alunoService.vincularTurma(idAluno, idTurma);
        return ResponseEntity.ok(alunoResponse);
    }

    @GetMapping("/listarAlunosComStatusDaMateria/{professor}/{serie}")
    public ResponseEntity<List<MateriaStatusProjection>> listarAlunosComStatusDaMateria (@PathVariable Long professor, @PathVariable Long serie) {
        List<MateriaStatusProjection> materiaStatusProjections = alunoService.listarAlunosComStatusDaMateria(professor, serie);
        return new ResponseEntity<>(materiaStatusProjections, HttpStatus.OK);
    }

    @GetMapping("/listarAlunoComStatusDaMateria/{professor}/{serie}/{aluno}")
    public ResponseEntity<MateriaStatusProjection> listarAlunoComStatusDaMateria (@PathVariable Long professor, @PathVariable Long serie, @PathVariable Long aluno) {
        MateriaStatusProjection materiaStatusProjections = alunoService.listarAlunoComStatusDaMateria(professor, serie, aluno);
        return new ResponseEntity<>(materiaStatusProjections, HttpStatus.OK);
    }
}
