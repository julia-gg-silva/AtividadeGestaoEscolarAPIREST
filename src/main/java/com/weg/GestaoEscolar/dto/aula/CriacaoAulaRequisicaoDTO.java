package com.weg.GestaoEscolar.dto.aula;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CriacaoAulaRequisicaoDTO(
        @PositiveOrZero(message = "O Campo deve ser maior que 0")
        int turmaId,

        @Past(message = "A data selecionada ainda não aconteceu! Selecione uma data do passado!")
        LocalDateTime data,

        @NotEmpty(message = "O campo não pode ser vazio!")
        String assunto
) {
}
