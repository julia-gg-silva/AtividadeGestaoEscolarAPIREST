package com.weg.GestaoEscolar.exceptions;

public class CursoNaoEncontradoExecption extends RuntimeException {
    public CursoNaoEncontradoExecption() {
        super("O Professor solicitado não existe!");
    }
}
