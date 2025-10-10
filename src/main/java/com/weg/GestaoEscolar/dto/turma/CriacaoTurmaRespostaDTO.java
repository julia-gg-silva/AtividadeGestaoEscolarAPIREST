package com.weg.GestaoEscolar.dto.turma;

import java.util.List;

public record CriacaoTurmaRespostaDTO(
        int id,
        String nome,
        String nomeCurso,
        String nomeProfessor,
        List<String> nomeAlunos
) {
}
