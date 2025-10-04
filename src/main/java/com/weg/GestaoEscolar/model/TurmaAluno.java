package com.weg.GestaoEscolar.model;

public class TurmaAluno {
    private int turma_id;
    private int aluno_id;

    public TurmaAluno(int turma_id, int aluno_id) {
        this.turma_id = turma_id;
        this.aluno_id = aluno_id;
    }

    public int getTurma_id() {
        return turma_id;
    }

    public void setTurma_id(int turma_id) {
        this.turma_id = turma_id;
    }

    public int getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(int aluno_id) {
        this.aluno_id = aluno_id;
    }
}
