package com.weg.GestaoEscolar.model;

import java.time.LocalDateTime;

public class Aula {
    private int id;
    private int idTurma;
    private LocalDateTime dataHora;
    private String assunto;

    public Aula(int id, int idTurma, LocalDateTime dataHora, String assunto) {
        this.id = id;
        this.idTurma = idTurma;
        this.dataHora = dataHora;
        this.assunto = assunto;
    }

    public Aula(int idTurma, LocalDateTime dataHora, String assunto) {
        this.idTurma = idTurma;
        this.dataHora = dataHora;
        this.assunto = assunto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
}
