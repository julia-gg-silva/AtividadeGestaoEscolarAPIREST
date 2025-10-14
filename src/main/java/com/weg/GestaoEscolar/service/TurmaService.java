package com.weg.GestaoEscolar.service;

import com.weg.GestaoEscolar.dto.turma.CriacaoTurmaRequisicaoDTO;
import com.weg.GestaoEscolar.dto.turma.CriacaoTurmaRespostaDTO;
import com.weg.GestaoEscolar.exceptions.TurmaNaoEncontradaException;
import com.weg.GestaoEscolar.mapper.TurmaMapper;
import com.weg.GestaoEscolar.model.Turma;
import com.weg.GestaoEscolar.model.TurmaResposta;
import com.weg.GestaoEscolar.repository.TurmaDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TurmaService {

    private final TurmaDAO repository;
    private final TurmaMapper mapper;


    public TurmaService(TurmaDAO repository, TurmaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CriacaoTurmaRespostaDTO criarTurma(CriacaoTurmaRequisicaoDTO requisicaoDTO) throws SQLException {
        Turma turma = mapper.paraEntidade(requisicaoDTO);

        Turma newTurma = repository.criarTurma(turma);
        repository.inserirAlunosTurma(newTurma.getId(), requisicaoDTO.idsAlunos());

        List<String> nomeAlunos = repository.buscarListaNomesPorId(requisicaoDTO.idsAlunos());

        TurmaResposta turmaResposta = repository.buscarTurmasPorId(newTurma.getId());

        return mapper.paraRespostaDTO(turmaResposta,nomeAlunos);
    }

    public List<CriacaoTurmaRespostaDTO> buscarTurmas() throws SQLException{
        List <TurmaResposta> turmas = repository.buscarTurmas();
        List<CriacaoTurmaRespostaDTO> respostaDTOS = new ArrayList<>();

        for(TurmaResposta turma : turmas){
            List<String> nomeAlunos = repository.buscarListaNomeAlunosPorTurma(turma.getId());
            respostaDTOS.add(mapper.paraRespostaDTO(turma,nomeAlunos));
        }
        return respostaDTOS;
    }

    public CriacaoTurmaRespostaDTO buscarTurmaPorId(int id) throws SQLException{
        TurmaResposta turma = repository.buscarTurmasPorId(id);

        if(turma == null){
            throw new TurmaNaoEncontradaException();
        }
        List<String> nomeAlunos = repository.buscarListaNomeAlunosPorTurma(id);

        return mapper.paraRespostaDTO(turma, nomeAlunos);
    }

    public CriacaoTurmaRespostaDTO atualizarTurma(
            int id, CriacaoTurmaRequisicaoDTO requisicaoDTO) throws SQLException{
        TurmaResposta turma = repository.buscarTurmasPorId(id);
        List<String> nomeAlunos = repository.buscarListaNomeAlunosPorTurma(id);

        if(turma == null){
            throw new TurmaNaoEncontradaException();
        }

        Turma newTurma = mapper.paraUpdate(requisicaoDTO, turma);
        repository.atualizarTurma(id, newTurma);
        TurmaResposta turmaResposta = new TurmaResposta(newTurma, turma.getNomeProfessor(), turma.getNomeCurso());
        return mapper.paraRespostaDTO(turmaResposta,nomeAlunos);
    }

    public void deletarTurma(int id) throws SQLException{
        TurmaResposta turma = repository.buscarTurmasPorId(id);

        if(turma == null){
            throw new TurmaNaoEncontradaException();
        }

        repository.deletarTurma(id);
    }
}
