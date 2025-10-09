package com.weg.GestaoEscolar.repository;

import com.weg.GestaoEscolar.database.Conexao;
import com.weg.GestaoEscolar.model.Curso;
import com.weg.GestaoEscolar.model.Turma;
import com.weg.GestaoEscolar.util.gerarIn;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TurmaDAO {

    public Turma criarTurma(Turma turma) throws SQLException {
        String query = "INSERT INTO turma(nome, curso_id, professor_id) VALUES(?,?,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, turma.getNome());
            stmt.setInt(2, turma.getCurso_id());
            stmt.setInt(3, turma.getProfessor_id());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                turma.setId(rs.getInt(1));
            }
        }
        return turma;
    }

    public List<Turma> listarTurmas() throws SQLException {
        String query = "SELECT id, nome, curso_id, professor_id FROM turma";
        List<Turma> turmas = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int cursoId = rs.getInt("curso_id");
                int professorId = rs.getInt("professor_id");

                Turma turma = new Turma(id, nome, cursoId, professorId);
                turmas.add(turma);
            }

        }
        return turmas;
    }

    public Turma buscarTurmasPorId(int id) throws SQLException {
        String query = "SELECT id, nome, curso_id, professor_id FROM turma WHERE id = ?";
        Turma turma = null;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int newId = rs.getInt("id");
                String nome = rs.getString("nome");
                int cursoId = rs.getInt("curso_id");
                int professorId = rs.getInt("professor_id");

                turma = new Turma(newId, nome, cursoId, professorId);
            }
        }
        return turma;
    }

    public Turma atualizarTurma(int id, Turma turma) throws SQLException {
        String query = "UPDATE turma SET nome = ?, curso_id = ?, professor_id = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, turma.getNome());
            stmt.setInt(2, turma.getCurso_id());
            stmt.setInt(3, turma.getProfessor_id());
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
        return turma;
    }

    public void deletarTurma(int id) throws SQLException {
        String query = "DELETE FROM turma WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    public boolean turmaExiste(int id) throws SQLException {
        String query = "SELECT id, nome, curso_id, professor_id FROM turma WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }
        }
        return false;
    }

    //Lista de professores que estão associadas ao curso
    public List<String> listaAlunosIds(List<Integer> idsAlunos) throws SQLException {
        String query = """
                SELECT p.nome
                FROM alunos a
                LEFT JOIN turma t
                ON a.id = t.professor_id
                WHERE p.id IN """ + gerarIn.gerando(idsAlunos.size());


        List<String> nomeProfessores = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            String nome = "";

            for (int i = 0; i < idsAlunos.size(); i++) {
                stmt.setInt(i + 1, idsAlunos.get(i));
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                nome = rs.getString("nome");
                nomeProfessores.add(nome);
            }

        }
        return nomeProfessores;
    }
}
