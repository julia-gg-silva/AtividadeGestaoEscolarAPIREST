package com.weg.GestaoEscolar.dto.curso;

import com.weg.GestaoEscolar.model.Professor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CriacaoCursoRequisicaoDTO(

       @NotBlank(message = "Nome inválido!")
        String nome,

       @NotBlank(message = "Código inválido!")
       String codigo,

        @NotEmpty(message = "Lista de professores inválidos!")
        List<Integer> listaProfessorIds
) {
}
