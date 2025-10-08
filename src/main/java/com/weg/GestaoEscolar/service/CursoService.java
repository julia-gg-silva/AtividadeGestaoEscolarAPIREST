package com.weg.GestaoEscolar.service;

import com.weg.GestaoEscolar.dto.curso.CriacaoCursoRequisicaoDTO;
import com.weg.GestaoEscolar.dto.curso.CriacaoCursoRespostaDTO;
import com.weg.GestaoEscolar.dto.professor.CriacaoProfessorRequisicaoDTO;
import com.weg.GestaoEscolar.dto.professor.CriacaoProfessorRespostaDTO;
import com.weg.GestaoEscolar.mapper.CursoMapper;
import com.weg.GestaoEscolar.mapper.ProfessorMapper;
import com.weg.GestaoEscolar.model.Curso;
import com.weg.GestaoEscolar.repository.CursoDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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

    public List<CriacaoCursoRespostaDTO> buscarTodos()throws SQLException {
        List<Curso> cursos = repository.listarCursos();


    }
}

