package com.weg.GestaoEscolar.dto.aluno;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;


public record CriacaoAlunoRequisicaoDTO(
        @NotBlank(message = "Nome inválido")
        String nome,

        @Email(message = "Email inválido")
        String email,

        @Positive
        String matricula,

        @Past(message = "A data de nascimento é inválida!")
        LocalDate dataNascimento
) {
}
