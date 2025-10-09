package com.weg.GestaoEscolar.mapper;

import com.weg.GestaoEscolar.dto.curso.CriacaoCursoRequisicaoDTO;
import com.weg.GestaoEscolar.dto.turma.CriacaoTurmaRequisicaoDTO;
import com.weg.GestaoEscolar.dto.turma.CriacaoTurmaRespostaDTO;
import com.weg.GestaoEscolar.model.Turma;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TurmaMapper {

    public Turma paraEntidade(CriacaoTurmaRequisicaoDTO requisicaoDTO) {
        return new Turma(requisicaoDTO.nome(), requisicaoDTO.cursoId(), requisicaoDTO.professorId());
    }

    public CriacaoTurmaRespostaDTO paraRespostaDTO(Turma turma, List<String> nomeAlunos) {
        return new CriacaoTurmaRespostaDTO(turma.getId(), turma.getNome(), turma.getCurso_id(), turma.getProfessor_id(), nomeAlunos);
    }

    public Turma paraUpdate(CriacaoTurmaRequisicaoDTO requisicaoDTO, Turma turma){

        if(requisicaoDTO.nome() != turma.getNome() && requisicaoDTO.nome() != null){
            turma.setNome(requisicaoDTO.nome());
        }

        if(requisicaoDTO.professorId() != turma.getProfessor_id() && requisicaoDTO.professorId() != 0){
            turma.setProfessor_id(requisicaoDTO.professorId());
        }

        if(requisicaoDTO.cursoId() != turma.getCurso_id() && requisicaoDTO.cursoId() != 0){
            turma.setCurso_id(requisicaoDTO.cursoId());
        }

        return turma;
    }
}
