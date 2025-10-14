package com.weg.GestaoEscolar.controller;

import com.weg.GestaoEscolar.dto.aula.CriacaoAulaRequisicaoDTO;
import com.weg.GestaoEscolar.dto.aula.CriacaoAulaRespostaDTO;
import com.weg.GestaoEscolar.service.AulaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/aulas")
public class AulaController {

    private final AulaService service;


    public AulaController(AulaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CriacaoAulaRespostaDTO> criarAula(
            @Valid @RequestBody CriacaoAulaRequisicaoDTO requisicaoDTO
    ) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.criarAula(requisicaoDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CriacaoAulaRespostaDTO>> buscarAulas() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarAulas());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriacaoAulaRespostaDTO> buscarAulasPorId(
            @PathVariable int id
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarAulaPorId(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriacaoAulaRespostaDTO> atualizarAula(
            @PathVariable int id, @Valid @RequestBody CriacaoAulaRequisicaoDTO requisicaoDTO
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.atualizarAula(id, requisicaoDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletarAula(
//            @PathVariable int id
//    ) {
//        try {
//            service.deletarAula(id);
//            return ResponseEntity.status(HttpStatus.OK)
//                    .build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .build();
//        }
//    }

}
