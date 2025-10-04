package com.weg.GestaoEscolar.dto.aluno;

import java.time.LocalDate;


public record CriacaoAlunoRequisicaoDTO(
        String nome,
        String email,
        String matricula,
        LocalDate dataNascimento
) {
}
