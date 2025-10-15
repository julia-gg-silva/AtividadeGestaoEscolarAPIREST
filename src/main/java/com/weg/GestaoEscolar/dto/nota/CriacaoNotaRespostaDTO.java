package com.weg.GestaoEscolar.dto.nota;

public record CriacaoNotaRespostaDTO(
        int id,
        String alunoNome,
        String aulaAssunto,
        double valor
) {
}
