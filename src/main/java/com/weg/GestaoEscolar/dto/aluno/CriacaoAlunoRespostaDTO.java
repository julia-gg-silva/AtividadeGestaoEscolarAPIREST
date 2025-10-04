package com.weg.GestaoEscolar.dto.aluno;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record CriacaoAlunoRespostaDTO(
        int id,
        String nome,
        String email,
        String matricula,
        LocalDate dataNascimento
) {
}
