package com.weg.GestaoEscolar.model;

import java.time.LocalDate;

public class Aluno {
    private int id;
    private String nome;
    private String email;
    private String matricula;
    private LocalDate data_nascimento;

    public Aluno(int id, String nome, String email, String matricula, LocalDate data_nascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.data_nascimento = data_nascimento;
    }

    public Aluno(String nome, String email, String matricula, LocalDate data_nascimento) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.data_nascimento = data_nascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
}
