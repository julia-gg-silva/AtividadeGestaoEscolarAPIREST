package com.weg.GestaoEscolar.controller;

import com.weg.GestaoEscolar.dto.aluno.CriacaoAlunoRequisicaoDTO;
import com.weg.GestaoEscolar.dto.aluno.CriacaoAlunoRespostaDTO;
import com.weg.GestaoEscolar.dto.professor.CriacaoProfessorRequisicaoDTO;
import com.weg.GestaoEscolar.dto.professor.CriacaoProfessorRespostaDTO;
import com.weg.GestaoEscolar.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService service;


    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CriacaoProfessorRespostaDTO> criarProfessor(
            @RequestBody CriacaoProfessorRequisicaoDTO requisicaoDTO) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.criarProfessor(requisicaoDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CriacaoProfessorRespostaDTO>> listarProfessores() {
        List<CriacaoProfessorRespostaDTO> professorResposta = null;

        try {
            professorResposta = service.listarProfessores();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(professorResposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriacaoProfessorRespostaDTO> buscarAlunoPorId(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarProfessorPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriacaoProfessorRespostaDTO> atualizarProfessor(
            @PathVariable int id, @RequestBody CriacaoProfessorRequisicaoDTO requisicaoDTO) {

        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.atualizarProfessor(id, requisicaoDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProfessor(
            @PathVariable int id
    ) {
        try {
            service.deletarProfessor(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
