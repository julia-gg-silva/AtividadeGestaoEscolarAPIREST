package com.weg.GestaoEscolar.service;

import com.weg.GestaoEscolar.dto.turma.CriacaoTurmaRequisicaoDTO;
import com.weg.GestaoEscolar.dto.turma.CriacaoTurmaRespostaDTO;
import com.weg.GestaoEscolar.mapper.TurmaMapper;
import com.weg.GestaoEscolar.model.Turma;
import com.weg.GestaoEscolar.model.TurmaResposta;
import com.weg.GestaoEscolar.repository.TurmaDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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

}
