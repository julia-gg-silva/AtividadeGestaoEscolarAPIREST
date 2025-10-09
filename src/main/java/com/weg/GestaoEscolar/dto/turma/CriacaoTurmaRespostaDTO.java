package com.weg.GestaoEscolar.dto.turma;

import java.util.List;

public record CriacaoTurmaRespostaDTO(
        int id,
        String nome,
        int cursoId,
        int professorID,
        List<String> nomeAlunos
) {
}
