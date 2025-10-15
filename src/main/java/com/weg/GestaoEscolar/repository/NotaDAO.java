package com.weg.GestaoEscolar.repository;

import com.weg.GestaoEscolar.database.Conexao;
import com.weg.GestaoEscolar.model.Nota;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NotaDAO {

    public Nota criarNota(Nota nota) throws SQLException {
        String query = "INSERT INTO nota (aluno_id, aula_id, valor) VALUES (?,?,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, nota.getAluno_id());
            stmt.setInt(2, nota.getAula_id());
            stmt.setDouble(3, nota.getValor());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                nota.setId(rs.getInt(1));
            }
        }
        return nota;
    }

    public List<Nota> buscarNotas() throws SQLException {
        String query = "SELECT id, aluno_id, aula_id, valor FROM nota";
        List<Nota> notas = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int alunoId = rs.getInt("aluno_id");
                int aulaId = rs.getInt("aula_id");
                double valor = rs.getDouble("valor");

                Nota nota = new Nota(id, alunoId, aulaId, valor);
                notas.add(nota);
            }
        }
        return notas;
    }

    public Nota buscarNotaPorId(int id) throws SQLException {
        String query = "SELECT id, aluno_id, aula_id, valor FROM nota WHERE id = ?";
        Nota nota = null;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int newId = rs.getInt("id");
                int alunoId = rs.getInt("aluno_id");
                int aulaId = rs.getInt("aula_id");
                double valor = rs.getDouble("valor");

                nota = new Nota(newId, alunoId, aulaId, valor);
            }
        }
        return nota;
    }

    public String buscarNomeAlunoPorId(int idAluno) throws SQLException {
        String query = "SELECT nome FROM aluno WHERE id = ?";
        String nome = null;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idAluno);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nome = rs.getString("nome");
            }
        }
        return nome;
    }

    public String buscarAssuntoAulaPorId(int idAula) throws SQLException {
        String query = "SELECT assunto FROM aula WHERE id = ?";
        String assunto = null;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idAula);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                assunto = rs.getString("assunto");
            }
        }
        return assunto;
    }

    public Nota atualizarNota(int id, Nota nota) throws SQLException {
        String query = "UPDATE nota SET aluno_id = ?, aula_id = ?, valor = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, nota.getAluno_id());
            stmt.setInt(2, nota.getAula_id());
            stmt.setDouble(3, nota.getValor());
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
        return nota;
    }

    public void deletarNota(int id) throws SQLException {
        String query = "DELETE FROM nota WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
