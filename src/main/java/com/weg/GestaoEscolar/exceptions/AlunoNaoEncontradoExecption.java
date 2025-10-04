package com.weg.GestaoEscolar.exceptions;

public class AlunoNaoEncontradoExecption extends RuntimeException{

    public AlunoNaoEncontradoExecption(){
        super("Aluno solicitado não existente");
    }
}
