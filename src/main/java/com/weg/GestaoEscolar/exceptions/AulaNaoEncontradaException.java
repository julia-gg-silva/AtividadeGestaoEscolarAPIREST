package com.weg.GestaoEscolar.exceptions;

public class AulaNaoEncontradaException extends RuntimeException{

    public AulaNaoEncontradaException(){
        super("A Aula solicitada não existe!");
    }
}
