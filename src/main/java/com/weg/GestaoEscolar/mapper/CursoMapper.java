package com.weg.GestaoEscolar.mapper;

import com.weg.GestaoEscolar.dto.curso.CriacaoCursoRequisicaoDTO;
import com.weg.GestaoEscolar.dto.curso.CriacaoCursoRespostaDTO;
import com.weg.GestaoEscolar.model.Curso;
import com.weg.GestaoEscolar.model.Professor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CursoMapper {

    public Curso paraEntidade(CriacaoCursoRequisicaoDTO requisicaoDTO){
        return new Curso(requisicaoDTO.nome(), requisicaoDTO.codigo());
    }

    public CriacaoCursoRespostaDTO paraRespostaDTO(Curso curso, List<String> professores){
        return new CriacaoCursoRespostaDTO(
                curso.getId(), curso.getNome(), curso.getCodigo(), professores);
    }

    public Curso paraUpdate(CriacaoCursoRequisicaoDTO requisicaoDTO, Curso curso){

        if(requisicaoDTO.nome() != curso.getNome() && requisicaoDTO.codigo() != null){
            curso.setNome(requisicaoDTO.nome());
        }

        if(requisicaoDTO.codigo() != curso.getCodigo() && requisicaoDTO.codigo() != null){
            curso.setCodigo(requisicaoDTO.codigo());
        }

        return curso;
    }
}
