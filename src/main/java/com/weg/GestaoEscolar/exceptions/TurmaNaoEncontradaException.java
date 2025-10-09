package com.weg.GestaoEscolar.exceptions;

public class TurmaNaoEncontradaException extends RuntimeException{

    public TurmaNaoEncontradaException(){
        super("Turma não existente!");
    }
}
