package com.weg.GestaoEscolar.dto.curso;

import java.util.List;

public record CriacaoCursoRespostaDTO(
        int id,
        String nome,
        String codigo,
        List<String>listaProfessores

) {
}
