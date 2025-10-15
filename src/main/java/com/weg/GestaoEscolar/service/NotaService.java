package com.weg.GestaoEscolar.service;

import com.weg.GestaoEscolar.dto.aula.CriacaoAulaRequisicaoDTO;
import com.weg.GestaoEscolar.dto.nota.CriacaoNotaRequisicaoDTO;
import com.weg.GestaoEscolar.dto.nota.CriacaoNotaRespostaDTO;
import com.weg.GestaoEscolar.exceptions.NotaNaoEncontradaException;
import com.weg.GestaoEscolar.mapper.NotaMapper;
import com.weg.GestaoEscolar.model.Nota;
import com.weg.GestaoEscolar.repository.NotaDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotaService {

    private final NotaDAO repository;
    private final NotaMapper mapper;


    public NotaService(NotaDAO repository, NotaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public CriacaoNotaRespostaDTO criarNota(CriacaoNotaRequisicaoDTO requisicaoDTO) throws SQLException {
        String nomeAluno = repository.buscarNomeAlunoPorId(requisicaoDTO.alunoId());
        String assuntoAula = repository.buscarAssuntoAulaPorId(requisicaoDTO.aulaId());

        return mapper.paraRespostaDTO(repository.criarNota(mapper.paraEntidade(requisicaoDTO)), nomeAluno, assuntoAula);
    }

    public List<CriacaoNotaRespostaDTO> buscarNotas() throws SQLException{
        List<Nota> notas = repository.buscarNotas();
        List<CriacaoNotaRespostaDTO> respostas = new ArrayList<>();

        for(Nota n : notas){
            String nomeAluno = repository.buscarNomeAlunoPorId(n.getAluno_id());
            String assuntoAula = repository.buscarAssuntoAulaPorId(n.getAula_id());

            CriacaoNotaRespostaDTO respostaDTO = new CriacaoNotaRespostaDTO(n.getId(), nomeAluno, assuntoAula, n.getValor());
            respostas.add(respostaDTO);
        }
        return respostas;
    }

    public CriacaoNotaRespostaDTO buscarNotaPorId(int id) throws SQLException{
        Nota nota = repository.buscarNotaPorId(id);

        if(nota == null){
            throw new NotaNaoEncontradaException();
        }

        String nomeAluno = repository.buscarNomeAlunoPorId(nota.getAluno_id());
        String assuntoAula = repository.buscarAssuntoAulaPorId(nota.getAula_id());

        return mapper.paraRespostaDTO(nota,nomeAluno,assuntoAula);
    }

    public CriacaoNotaRespostaDTO atualizarNota(int id, CriacaoNotaRequisicaoDTO requisicaoDTO) throws SQLException{
        Nota nota = repository.buscarNotaPorId(id);

        if(nota == null){
            throw new NotaNaoEncontradaException();
        }

        Nota newNota = mapper.paraUpdate(nota, requisicaoDTO);
        repository.atualizarNota(id, newNota);

        String nomeAluno = repository.buscarNomeAlunoPorId(newNota.getAluno_id());
        String assuntoAula = repository.buscarAssuntoAulaPorId(newNota.getAula_id());

        return mapper.paraRespostaDTO(newNota, nomeAluno, assuntoAula);
    }

    public void deletarNota(int id) throws SQLException{
        Nota nota = repository.buscarNotaPorId(id);

        if(nota == null){
            throw new NotaNaoEncontradaException();
        }

        repository.deletarNota(id);
    }
}
