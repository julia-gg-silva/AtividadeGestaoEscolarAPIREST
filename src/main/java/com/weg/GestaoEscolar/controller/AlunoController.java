package com.weg.GestaoEscolar.controller;

import com.weg.GestaoEscolar.dto.aluno.CriacaoAlunoRequisicaoDTO;
import com.weg.GestaoEscolar.dto.aluno.CriacaoAlunoRespostaDTO;
import com.weg.GestaoEscolar.model.Aluno;
import com.weg.GestaoEscolar.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private AlunoService service;

    public AlunoController(AlunoService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CriacaoAlunoRespostaDTO> criarAluno(
            @RequestBody CriacaoAlunoRequisicaoDTO requisicaoDTO){
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.criarAluno(requisicaoDTO));

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CriacaoAlunoRespostaDTO>> listarAlunos(){
        List<CriacaoAlunoRespostaDTO> listaRespostas = new ArrayList<>();

        try{
            listaRespostas = service.listarAlunos();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(listaRespostas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriacaoAlunoRespostaDTO> buscarAlunoPorId(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarAlunoId(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriacaoAlunoRespostaDTO> atualizarAluno(
            @PathVariable int id, @RequestBody CriacaoAlunoRequisicaoDTO requisicaoDTO
    ){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.atualizarAluno(id, requisicaoDTO));

        }catch (Exception e){;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable int id){

        try{
            service.deletarAluno(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
