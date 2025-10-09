package com.weg.GestaoEscolar.dto.professor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CriacaoProfessorRequisicaoDTO(

        @NotBlank(message = "Nome inválido!")
        String nome,

        @Email(message = "Email inválido!")
        String email,

        @NotBlank(message = "Nome da disciplina inválida!")
        String disciplina
) {
}
