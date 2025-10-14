package com.weg.GestaoEscolar.dto.aula;


import java.time.LocalDateTime;

public record CriacaoAulaRespostaDTO(
        int id,
        String turmaNome,
        LocalDateTime data,
        String assunto
) {
}
