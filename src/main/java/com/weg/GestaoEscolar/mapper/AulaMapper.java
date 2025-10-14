package com.weg.GestaoEscolar.mapper;

import com.weg.GestaoEscolar.dto.aula.CriacaoAulaRequisicaoDTO;
import com.weg.GestaoEscolar.dto.aula.CriacaoAulaRespostaDTO;
import com.weg.GestaoEscolar.model.Aula;
import org.springframework.stereotype.Component;

@Component
public class AulaMapper {

    public Aula paraEntidade(CriacaoAulaRequisicaoDTO requisicaoDTO) {
        return new Aula(requisicaoDTO.turmaId(), requisicaoDTO.data(), requisicaoDTO.assunto());
    }

    public CriacaoAulaRespostaDTO paraRespostaDTO(Aula aula, String nomeTurma) {
        return new CriacaoAulaRespostaDTO(aula.getId(), nomeTurma, aula.getDataHora(), aula.getAssunto());
    }

    public Aula paraUpdate(Aula aula, CriacaoAulaRequisicaoDTO requisicaoDTO) {
        if (requisicaoDTO.turmaId() != 0 && requisicaoDTO.turmaId() != aula.getIdTurma()) {
            aula.setIdTurma(requisicaoDTO.turmaId());
        }

        if (requisicaoDTO.data() != null && !requisicaoDTO.data().equals(aula.getDataHora())) {
            aula.setDataHora(requisicaoDTO.data());
        }

        if (requisicaoDTO.assunto() != null && !requisicaoDTO.assunto().equals(aula.getAssunto())) {
            aula.setAssunto(requisicaoDTO.assunto());
        }

        return aula;
    }

}
