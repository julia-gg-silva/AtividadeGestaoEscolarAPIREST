package com.weg.GestaoEscolar.exceptions;

public class ProfessotNaoEncontradoExeption extends RuntimeException{

    public ProfessotNaoEncontradoExeption(){
        super("O Professor solicitado não existe!");
    }
}
