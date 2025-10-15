package com.weg.GestaoEscolar.exceptions;

public class NotaNaoEncontradaException extends RuntimeException{

    public NotaNaoEncontradaException(){
        super("Nota solicitada não encontrada!");
    }
}
