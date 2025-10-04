package com.weg.GestaoEscolar.repository;

import com.weg.GestaoEscolar.database.Conexao;
import com.weg.GestaoEscolar.model.Curso;
import com.weg.GestaoEscolar.util.gerarIn;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoDAO {

    public Curso criarCurso(Curso curso) throws SQLException {
        String query = "INSERT INTO curso(nome, codigo) VALUES(?,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCodigo());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                curso.setId(rs.getInt(1));
            }
        }
        return curso;
    }

    public List<Curso> listarCursos() throws SQLException {
        String query = "SELECT id, nome, codigo FROM curso";
        List<Curso> cursos = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String codigo = rs.getString("codigo");

                Curso curso = new Curso(id, nome, codigo);
                cursos.add(curso);
            }

        }
        return cursos;
    }

    public Curso buscarCursosPorId(int id) throws SQLException {
        String query = "SELECT id, nome, codigo FROM curso WHERE id = ?";
        Curso curso = null;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int newId = rs.getInt("id");
                String nome = rs.getString("nome");
                String codigo = rs.getString("codigo");

                curso = new Curso(newId, nome, codigo);
            }
        }
        return curso;
    }

    public Curso atualizarCurso(int id, Curso curso) throws SQLException {
        String query = "UPDATE curso SET nome = ?, codigo = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCodigo());
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
        return curso;
    }

    public void deletarCurso(int id) throws SQLException {
        String query = "DELETE FROM curso WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    public boolean cursoExiste(int id) throws SQLException {
        String query = "SELECT id, nome, codigo FROM curso WHERE id = ?";

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
    public List<String> listaProfessorNome(List<Integer> idsProfessores) throws SQLException {
        String query = """
                SELECT p.nome
                FROM professor p
                LEFT JOIN turma t
                ON p.id = t.professor_id
                WHERE p.id IN """+ gerarIn.gerando(idsProfessores.size());
        String var = gerarIn.gerando(idsProfessores.size());


        List<String> nomeProfessores = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            String nome = "";

            for(int i = 0; i < idsProfessores.size(); i++){
                stmt.setInt(i + 1, idsProfessores.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
               nome = rs.getString("nome");
               nomeProfessores.add(nome);
            }

        }
        return nomeProfessores;
    }
}

