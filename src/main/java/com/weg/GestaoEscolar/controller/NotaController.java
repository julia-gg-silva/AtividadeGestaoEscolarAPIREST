package com.weg.GestaoEscolar.controller;

import com.weg.GestaoEscolar.dto.nota.CriacaoNotaRequisicaoDTO;
import com.weg.GestaoEscolar.dto.nota.CriacaoNotaRespostaDTO;
import com.weg.GestaoEscolar.service.NotaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notas")
public class NotaController {

    private final NotaService service;

    public NotaController(NotaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CriacaoNotaRespostaDTO> criarNota(
            @Valid @RequestBody CriacaoNotaRequisicaoDTO requisicaoDTO
    ) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.criarNota(requisicaoDTO));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CriacaoNotaRespostaDTO>> buscarNotas() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarNotas());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriacaoNotaRespostaDTO> buscarNotaPorId(
            @PathVariable int id
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarNotaPorId(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriacaoNotaRespostaDTO> atualizarNota(
            @PathVariable int id, @Valid @RequestBody CriacaoNotaRequisicaoDTO requisicaoDTO
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.atualizarNota(id, requisicaoDTO));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarNota(@PathVariable int id){
        try{
            service.deletarNota(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .build();

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
