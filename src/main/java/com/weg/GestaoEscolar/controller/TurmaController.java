package com.weg.GestaoEscolar.controller;

import com.weg.GestaoEscolar.dto.turma.CriacaoTurmaRequisicaoDTO;
import com.weg.GestaoEscolar.dto.turma.CriacaoTurmaRespostaDTO;
import com.weg.GestaoEscolar.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService service;

    public TurmaController(TurmaService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<CriacaoTurmaRespostaDTO> criarTurma(
            @Valid @RequestBody CriacaoTurmaRequisicaoDTO requisicaoDTO
    ) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.criarTurma(requisicaoDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }


    @GetMapping
    public ResponseEntity<List<CriacaoTurmaRespostaDTO>> buscarTodos() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarTurmas());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriacaoTurmaRespostaDTO> buscarTurmaPorId(
            @PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarTurmaPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriacaoTurmaRespostaDTO> atualizarTurma(
            @PathVariable int id, @Valid @RequestBody CriacaoTurmaRequisicaoDTO requisicaoDTO
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.atualizarTurma(id, requisicaoDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTurma(
            @PathVariable int id
    ){
        try {
            service.deletarTurma(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
    }


