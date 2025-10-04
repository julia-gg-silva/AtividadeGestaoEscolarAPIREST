package com.weg.GestaoEscolar.dto.curso;

import com.weg.GestaoEscolar.model.Professor;

import java.util.List;

public record CriacaoCursoRequisicaoDTO(
        String nome,
        String codigo,
        List<Integer> listaProfessorIds
) {
}
