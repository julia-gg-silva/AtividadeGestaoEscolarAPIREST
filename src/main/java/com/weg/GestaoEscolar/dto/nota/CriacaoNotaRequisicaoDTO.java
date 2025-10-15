package com.weg.GestaoEscolar.dto.nota;

import jakarta.validation.constraints.*;

public record CriacaoNotaRequisicaoDTO(
        @PositiveOrZero(message = "O ID deve ser maior que 0")
        int alunoId,
        @PositiveOrZero(message = "O ID deve ser maior que 0")
        int aulaId,

        @Positive(message = "O valor deve ser positivo")
                @Max(value = 10, message = "O valor deve ser no máximo 10")
        double valor
) {
}
