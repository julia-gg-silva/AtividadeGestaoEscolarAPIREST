package com.weg.GestaoEscolar.service;

import com.weg.GestaoEscolar.dto.professor.CriacaoProfessorRequisicaoDTO;
import com.weg.GestaoEscolar.dto.professor.CriacaoProfessorRespostaDTO;
import com.weg.GestaoEscolar.exceptions.ProfessotNaoEncontradoExeption;
import com.weg.GestaoEscolar.mapper.ProfessorMapper;
import com.weg.GestaoEscolar.model.Professor;
import com.weg.GestaoEscolar.repository.ProfessorDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorDAO repository;
    private final ProfessorMapper mapper;


    public ProfessorService(ProfessorDAO repository, ProfessorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CriacaoProfessorRespostaDTO criarProfessor(
            CriacaoProfessorRequisicaoDTO requisicaoDTO) throws SQLException {
        return mapper.paraRespostaDTO(repository.criarProfessor(mapper.paraEntidade(requisicaoDTO)));
    }

    public List<CriacaoProfessorRespostaDTO> listarProfessores() throws SQLException{
        return repository.listarProfessores().stream()
                .map(mapper::paraRespostaDTO)
                .toList();

    }

    public CriacaoProfessorRespostaDTO buscarProfessorPorId(int id) throws SQLException{
        Professor professor = repository.buscarProfessorPorId(id);

        if(professor == null){
            throw new ProfessotNaoEncontradoExeption();
        }

        return mapper.paraRespostaDTO(professor);
    }

    public CriacaoProfessorRespostaDTO atualizarProfessor(
            int id, CriacaoProfessorRequisicaoDTO requisicaoDTO) throws SQLException{
        Professor professor = repository.buscarProfessorPorId(id);

        if(professor == null){
            throw new ProfessotNaoEncontradoExeption();
        }

        Professor newProfessor =  mapper.paraUpdate(requisicaoDTO, professor);
        repository.atualizarProfessor(id, newProfessor);
        return mapper.paraRespostaDTO(newProfessor);
    }

    public void deletarProfessor(int id) throws SQLException{
        if(!repository.professorExiste(id)){
            throw new ProfessotNaoEncontradoExeption();
        }

        repository.deletarProfessor(id);
    }
}
