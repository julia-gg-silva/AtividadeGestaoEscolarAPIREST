package com.weg.GestaoEscolar.controller;

import com.weg.GestaoEscolar.dto.curso.CriacaoCursoRequisicaoDTO;
import com.weg.GestaoEscolar.dto.curso.CriacaoCursoRespostaDTO;
import com.weg.GestaoEscolar.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService service;


    public CursoController(CursoService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<CriacaoCursoRespostaDTO> criarCurso(
            @RequestBody CriacaoCursoRequisicaoDTO requisicaoDTO){

        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.criarCurso(requisicaoDTO));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

}
