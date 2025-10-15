package com.weg.GestaoEscolar.service;

import com.weg.GestaoEscolar.dto.aula.CriacaoAulaRequisicaoDTO;
import com.weg.GestaoEscolar.dto.aula.CriacaoAulaRespostaDTO;
import com.weg.GestaoEscolar.dto.curso.CriacaoCursoRespostaDTO;
import com.weg.GestaoEscolar.exceptions.AulaNaoEncontradaException;
import com.weg.GestaoEscolar.mapper.AulaMapper;
import com.weg.GestaoEscolar.model.Aula;
import com.weg.GestaoEscolar.repository.AulaDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AulaService {

    private final AulaDAO repository;
    private final AulaMapper mapper;


    public AulaService(AulaDAO repository, AulaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CriacaoAulaRespostaDTO criarAula(CriacaoAulaRequisicaoDTO requisicaoDTO) throws SQLException {
        Aula aula = repository.criarAula(mapper.paraEntidade(requisicaoDTO));
        String nomeTurma = repository.buscarNomeTurmaPorId(aula.getIdTurma());

        return mapper.paraRespostaDTO(aula, nomeTurma);
    }

    public List<CriacaoAulaRespostaDTO> buscarAulas() throws SQLException{
        List<Aula> aulas = repository.buscarAulas();
        List<CriacaoAulaRespostaDTO> respostaDTO = new ArrayList<>();

        for(Aula a : aulas){
            String nomeTurma = repository.buscarNomeTurmaPorId(a.getIdTurma());

            respostaDTO.add(mapper.paraRespostaDTO(a,nomeTurma));
        }
        return respostaDTO;
    }

    public CriacaoAulaRespostaDTO buscarAulaPorId(int id) throws SQLException{
        Aula aula = repository.bucarAulaPorId(id);

        if(aula == null){
            throw new AulaNaoEncontradaException();
        }

        String nomeTurma = repository.buscarNomeTurmaPorId(aula.getIdTurma());
        return mapper.paraRespostaDTO(repository.bucarAulaPorId(id),nomeTurma);
    }

    public CriacaoAulaRespostaDTO atualizarAula(int id, CriacaoAulaRequisicaoDTO requisicaoDTO) throws SQLException {
        Aula aula = repository.bucarAulaPorId(id);

        if(aula == null){
            throw new AulaNaoEncontradaException();
        }

        Aula newAula = mapper.paraUpdate(aula,requisicaoDTO);
        repository.atualizarAula(id,newAula);
        String nomeTurma = repository.buscarNomeTurmaPorId(newAula.getIdTurma());

        return mapper.paraRespostaDTO(newAula, nomeTurma);
    }

    public void deletarAula(int id) throws SQLException{
        Aula aula = repository.bucarAulaPorId(id);

        if(aula == null){
            throw new AulaNaoEncontradaException();
        }

        repository.deletarAula(id);
    }
}
