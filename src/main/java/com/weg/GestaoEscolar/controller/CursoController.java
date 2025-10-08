package com.weg.GestaoEscolar.controller;

import com.weg.GestaoEscolar.dto.curso.CriacaoCursoRequisicaoDTO;
import com.weg.GestaoEscolar.dto.curso.CriacaoCursoRespostaDTO;
import com.weg.GestaoEscolar.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<List<CriacaoCursoRespostaDTO>> listarCursos(){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarTodos());
        }catch (Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriacaoCursoRespostaDTO> buscarCursoPorId(@PathVariable int id){
        try{
          return ResponseEntity.status(HttpStatus.OK)
                  .body(service.buscarCursoPorId(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriacaoCursoRespostaDTO> atualizarCurso
            (@PathVariable int id, @RequestBody CriacaoCursoRequisicaoDTO requisicaoDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.atualizarCurso(id, requisicaoDTO));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable int id){
        try{
            service.deletarCurso(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
