package com.weg.GestaoEscolar.service;

import com.weg.GestaoEscolar.dto.aluno.CriacaoAlunoRequisicaoDTO;
import com.weg.GestaoEscolar.dto.aluno.CriacaoAlunoRespostaDTO;
import com.weg.GestaoEscolar.exceptions.AlunoNaoEncontradoExecption;
import com.weg.GestaoEscolar.mapper.AlunoMapper;
import com.weg.GestaoEscolar.model.Aluno;
import com.weg.GestaoEscolar.repository.AlunoDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AlunoService {

    private final AlunoDAO repository;
    private final AlunoMapper mapper;

    public AlunoService(AlunoDAO repository, AlunoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public CriacaoAlunoRespostaDTO criarAluno(
            CriacaoAlunoRequisicaoDTO requisicaoDTO) throws SQLException {
        return mapper.paraRespostaDTO(repository.criarALuno(mapper.paraEntidade(requisicaoDTO)));
    }

    public List<CriacaoAlunoRespostaDTO> listarAlunos() throws SQLException{
        return repository.listarAlunos().stream()
                .map(mapper::paraRespostaDTO)
                .toList();
    }

    public CriacaoAlunoRespostaDTO buscarAlunoId(int id) throws SQLException{
        Aluno aluno = repository.buscarAlunoPorId(id);

        if(aluno == null){
            throw  new AlunoNaoEncontradoExecption();
        }
        return mapper.paraRespostaDTO(aluno);
    }

    public CriacaoAlunoRespostaDTO atualizarAluno
            (int id, CriacaoAlunoRequisicaoDTO requisicaoDTO) throws SQLException{
        Aluno aluno = repository.buscarAlunoPorId(id);

        if(aluno == null){
            throw new AlunoNaoEncontradoExecption();
        }

        Aluno newAluno = mapper.paraUpdate(requisicaoDTO, aluno);
        repository.atualizarAluno(id, newAluno);
        return mapper.paraRespostaDTO(newAluno);
    }

    public void deletarAluno(int id) throws SQLException{
        if(!repository.existeAlunoPorId(id)){
            throw new AlunoNaoEncontradoExecption();
        }

        repository.detelarAluno(id);
    }
}
