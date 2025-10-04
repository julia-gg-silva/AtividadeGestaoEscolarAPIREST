package com.weg.GestaoEscolar.repository;

import com.weg.GestaoEscolar.database.Conexao;
import com.weg.GestaoEscolar.model.Professor;
import org.springframework.stereotype.Repository;

import java.beans.PropertyDescriptor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfessorDAO {

    public Professor criarProfessor(Professor professor) throws SQLException {
        String query = "INSERT INTO professor(nome, email, disciplina) VALUES(?,?,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getDisciplina());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                professor.setId(rs.getInt(1));
            }
        }
        return professor;
    }

    public List<Professor> listarProfessores() throws SQLException {
        String query = "SELECT id, nome, email, disciplina FROM professor";
        List<Professor> professors = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String disciplina = rs.getString("disciplina");

                Professor professor = new Professor(id, nome, email, disciplina);
                professors.add(professor);

            }
        }
        return professors;
    }

    public Professor buscarProfessorPorId(int id) throws SQLException {
        String query = "SELECT id, nome, email, disciplina FROM professor WHERE id = ?";
        Professor professor = null;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                int newId = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String disciplina = rs.getString("disciplina");

                professor = new Professor(newId, nome, email, disciplina);
            }
        }
        return professor;
    }

    public Professor atualizarProfessor(int id, Professor professor) throws SQLException{
        String query = "UPDATE professor SET nome = ?, email = ?, disciplina = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getDisciplina());
            stmt.setInt(4, id);

            stmt.executeUpdate();
        }
        return professor;
    }

    public void deletarProfessor(int id) throws SQLException{
        String query = "DELETE FROM professor WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public boolean professorExiste(int id) throws SQLException{
        String query = "SELECT id, nome, email, disciplina FROM professor WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return true;
            }
        }
        return false;
    }
}
