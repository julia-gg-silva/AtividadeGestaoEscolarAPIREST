package com.weg.GestaoEscolar.service;

import com.weg.GestaoEscolar.dto.curso.CriacaoCursoRequisicaoDTO;
import com.weg.GestaoEscolar.dto.curso.CriacaoCursoRespostaDTO;
import com.weg.GestaoEscolar.dto.professor.CriacaoProfessorRequisicaoDTO;
import com.weg.GestaoEscolar.dto.professor.CriacaoProfessorRespostaDTO;
import com.weg.GestaoEscolar.exceptions.CursoNaoEncontradoExecption;
import com.weg.GestaoEscolar.mapper.CursoMapper;
import com.weg.GestaoEscolar.mapper.ProfessorMapper;
import com.weg.GestaoEscolar.model.Curso;
import com.weg.GestaoEscolar.repository.CursoDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CursoService {

    private final CursoDAO repository;
    private final CursoMapper mapper;
    private final Map<Integer, List<Integer>> cursoProfessoresMap;


    public CursoService(CursoDAO repository, CursoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.cursoProfessoresMap = new HashMap<>();
    }


    public CriacaoCursoRespostaDTO criarCurso(CriacaoCursoRequisicaoDTO requisicaoDTO) throws SQLException {
        List<String> nomeProfessores = repository.listaProfessorNome(requisicaoDTO.listaProfessorIds());

        Curso cursoCriado = repository.criarCurso(mapper.paraEntidade(requisicaoDTO));

        cursoProfessoresMap.put(cursoCriado.getId(), requisicaoDTO.listaProfessorIds());

        return mapper.paraRespostaDTO(cursoCriado, nomeProfessores);
    }

    public List<CriacaoCursoRespostaDTO> buscarTodos() throws SQLException {
        List<Curso> cursos = repository.listarCursos();
        List<CriacaoCursoRespostaDTO> respostaDTOS = new ArrayList<>();

        for (Curso c : cursos) {
            List<Integer> idsProfessores = cursoProfessoresMap.getOrDefault(c.getId(), List.of());
            List<String> nomesProfessores =new ArrayList<>();

            if (!idsProfessores.isEmpty()) {
                nomesProfessores = repository.listaProfessorNome(idsProfessores);
            }

            respostaDTOS.add(mapper.paraRespostaDTO(c, nomesProfessores));
        }

        return respostaDTOS;

    }

    public CriacaoCursoRespostaDTO buscarCursoPorId(int id) throws SQLException{
        Curso curso = repository.buscarCursosPorId(id);
        List<Curso> cursos = repository.listarCursos();

        if(curso == null){
            throw new CursoNaoEncontradoExecption();
        }
        List<Integer> idsProfessores = new ArrayList<>();
        List<String> nomeProfessor = new ArrayList<>();

        for(Curso c : cursos){
            if(c.getId() == id){
                idsProfessores = cursoProfessoresMap.getOrDefault(c.getId(), List.of());


                if(!idsProfessores.isEmpty()){
                    nomeProfessor = repository.listaProfessorNome(idsProfessores);

                }
            }
        }
        return mapper.paraRespostaDTO(curso, nomeProfessor);
    }

    public CriacaoCursoRespostaDTO atualizarCurso(int id, CriacaoCursoRequisicaoDTO requisicaoDTO) throws SQLException {
        Curso curso = repository.buscarCursosPorId(id);

        if (curso == null) {
            throw new CursoNaoEncontradoExecption();
        }

        List<Integer> idProfessor = cursoProfessoresMap.getOrDefault(id, List.of());
        List<String> nomeProfessor = new ArrayList<>();

        if(!idProfessor.isEmpty()){
            nomeProfessor = repository.listaProfessorNome(idProfessor);
        }

        Curso newCurso = mapper.paraUpdate(requisicaoDTO, curso);
        repository.atualizarCurso(id, newCurso);
        return mapper.paraRespostaDTO(newCurso, nomeProfessor);
    }

    public void deletarCurso(int id) throws SQLException{
        Curso curso = repository.buscarCursosPorId(id);

        if (curso == null) {
            throw new CursoNaoEncontradoExecption();
        }

        repository.deletarCurso(id);
    }
}

