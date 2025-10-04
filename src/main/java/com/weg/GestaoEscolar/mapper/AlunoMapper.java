package com.weg.GestaoEscolar.mapper;

import com.weg.GestaoEscolar.dto.aluno.CriacaoAlunoRequisicaoDTO;
import com.weg.GestaoEscolar.dto.aluno.CriacaoAlunoRespostaDTO;
import com.weg.GestaoEscolar.model.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    public Aluno paraEntidade(CriacaoAlunoRequisicaoDTO requisicaoDTO){
        return new Aluno(requisicaoDTO.nome(), requisicaoDTO.email(), requisicaoDTO.matricula(), requisicaoDTO.dataNascimento());

    }

    public CriacaoAlunoRespostaDTO paraRespostaDTO(Aluno aluno){
        return new CriacaoAlunoRespostaDTO(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getMatricula(), aluno.getData_nascimento());
    }

    public Aluno paraUpdate(CriacaoAlunoRequisicaoDTO requisicaoDTO, Aluno aluno){

        if(requisicaoDTO.nome() != aluno.getNome() && requisicaoDTO.nome() != null){
            aluno.setNome(requisicaoDTO.nome());
        }

        if(requisicaoDTO.email() != aluno.getEmail() && requisicaoDTO.email() != null){
            aluno.setEmail(requisicaoDTO.email());
        }

        if(requisicaoDTO.matricula() != aluno.getMatricula() && requisicaoDTO.matricula() != null){
            aluno.setMatricula(requisicaoDTO.matricula());
        }

        if(requisicaoDTO.dataNascimento() != aluno.getData_nascimento() && requisicaoDTO.dataNascimento() != null){
            aluno.setData_nascimento(requisicaoDTO.dataNascimento());
        }
        return aluno;
    }
}
