package com.weg.GestaoEscolar.dto.professor;

import java.time.LocalDate;

public record CriacaoProfessorRespostaDTO(
        int id,
        String nome,
        String email,
        String matricula
) {
}
