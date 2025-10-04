package com.weg.GestaoEscolar.service;

import com.weg.GestaoEscolar.dto.curso.CriacaoCursoRequisicaoDTO;
import com.weg.GestaoEscolar.dto.curso.CriacaoCursoRespostaDTO;
import com.weg.GestaoEscolar.dto.professor.CriacaoProfessorRequisicaoDTO;
import com.weg.GestaoEscolar.dto.professor.CriacaoProfessorRespostaDTO;
import com.weg.GestaoEscolar.mapper.CursoMapper;
import com.weg.GestaoEscolar.mapper.ProfessorMapper;
import com.weg.GestaoEscolar.repository.CursoDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CursoService {

    private final CursoDAO repository;
    private final CursoMapper mapper;


    public CursoService(CursoDAO repository, CursoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public CriacaoCursoRespostaDTO criarCurso(CriacaoCursoRequisicaoDTO requisicaoDTO) throws SQLException {
        List<String> nomeProfessores = repository.listaProfessorNome(requisicaoDTO.listaProfessorIds());

        return mapper.paraRespostaDTO(repository.criarCurso(mapper.paraEntidade(requisicaoDTO)), nomeProfessores);

    }

/*    public List<CriacaoProfessorRespostaDTO> listarCursos() throws SQLException{
        return repository.listarCursos().stream()
                .map(mapper::paraRespostaDTO)
                .toList();

    }*/

}

