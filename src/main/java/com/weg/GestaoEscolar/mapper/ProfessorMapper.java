package com.weg.GestaoEscolar.mapper;

import com.weg.GestaoEscolar.dto.aluno.CriacaoAlunoRequisicaoDTO;
import com.weg.GestaoEscolar.dto.professor.CriacaoProfessorRequisicaoDTO;
import com.weg.GestaoEscolar.dto.professor.CriacaoProfessorRespostaDTO;
import com.weg.GestaoEscolar.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    public Professor paraEntidade(CriacaoProfessorRequisicaoDTO requisicaoDTO){
        return new Professor(requisicaoDTO.nome(), requisicaoDTO.email(), requisicaoDTO.disciplina());

    }

    public CriacaoProfessorRespostaDTO paraRespostaDTO(Professor professor){
        return new CriacaoProfessorRespostaDTO(
                professor.getId(), professor.getNome(), professor.getEmail(), professor.getDisciplina());
    }

    public Professor paraUpdate(CriacaoProfessorRequisicaoDTO requisicaoDTO, Professor professor){

        if(requisicaoDTO.nome() != professor.getNome() && requisicaoDTO.nome() != null){
            professor.setNome(requisicaoDTO.nome());
        }

        if(requisicaoDTO.email() != professor.getEmail() & requisicaoDTO.email() != null){
            professor.setEmail(requisicaoDTO.email());
        }

        if(requisicaoDTO.disciplina() != professor.getDisciplina() && requisicaoDTO.disciplina() != null){
            professor.setDisciplina(requisicaoDTO.disciplina());
        }

        return professor;
    }
}
