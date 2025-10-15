package com.weg.GestaoEscolar.mapper;

import com.weg.GestaoEscolar.dto.nota.CriacaoNotaRequisicaoDTO;
import com.weg.GestaoEscolar.dto.nota.CriacaoNotaRespostaDTO;
import com.weg.GestaoEscolar.model.Nota;
import org.springframework.stereotype.Component;

@Component
public class NotaMapper {

    public Nota paraEntidade(CriacaoNotaRequisicaoDTO requisicaoDTO){
        return new Nota(requisicaoDTO.alunoId(), requisicaoDTO.aulaId(), requisicaoDTO.valor());
    }

    public CriacaoNotaRespostaDTO paraRespostaDTO(Nota nota, String nomeAluno, String assuntoAula){
        return new CriacaoNotaRespostaDTO(nota.getId(), nomeAluno, assuntoAula, nota.getValor());
    }

    public Nota paraUpdate(Nota nota, CriacaoNotaRequisicaoDTO requisicaoDTO){

        if(nota.getAluno_id() == requisicaoDTO.alunoId() && requisicaoDTO.alunoId() != 0){
            nota.setAluno_id(requisicaoDTO.aulaId());
        }

        if(nota.getAula_id() == requisicaoDTO.aulaId() && requisicaoDTO.aulaId() != 0){
            nota.setAula_id(requisicaoDTO.aulaId());
        }

        if(nota.getValor() == requisicaoDTO.valor() && requisicaoDTO.valor() != 0){
            nota.setValor(requisicaoDTO.valor());
        }
        return nota;
    }
}
