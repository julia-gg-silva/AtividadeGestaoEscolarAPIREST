package com.weg.GestaoEscolar.repository;

import com.weg.GestaoEscolar.database.Conexao;
import com.weg.GestaoEscolar.model.Aula;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Repository
public class AulaDAO {

    public Aula criarAula(Aula aula) throws SQLException {
        String query = "INSERT INTO aula (turma_id, data_hora, assunto) VALUES (?,?,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {


            stmt.setInt(1, aula.getIdTurma());
            stmt.setTimestamp(2, Timestamp.valueOf(aula.getDataHora()));
            stmt.setString(3, aula.getAssunto());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) ;
            aula.setId(rs.getInt(1));
        }
        return aula;
    }

    public List<Aula> buscarAulas() throws SQLException {
        String query = "SELECT id, turma_id, data_hora, assunto FROM aula";
        List<Aula> aulas = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {


            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int idTurma = rs.getInt("turma_id");
                LocalDateTime data = rs.getTimestamp("data_hora").toLocalDateTime();
                String assunto = rs.getString("assunto");

                Aula aula = new Aula(id, idTurma, data, assunto);
                aulas.add(aula);
            }
        }
        return aulas;
    }

    public String buscarNomeTurmaPorId(int idTurma) throws SQLException {
        String query = "SELECT nome FROM turma WHERE id = ?";
        String nomeTurma = null;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idTurma);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nomeTurma = rs.getString("nome");
            }
        }
        return nomeTurma;
    }

    public Aula bucarAulaPorId(int idAula) throws SQLException {
        String query = "SELECT id, turma_id, data_hora, assunto FROM aula WHERE id = ?";
        Aula aula = null;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idAula);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                int idTurma = rs.getInt("turma_id");
                LocalDateTime dataHora = rs.getTimestamp("data_hora").toLocalDateTime();
                String assunto = rs.getString("assunto");

                aula = new Aula(id, idTurma, dataHora, assunto);
            }
        }
        return aula;
    }

    public Aula atualizarAula(int id, Aula aula) throws SQLException{
        String query = "UPDATE aula SET turma_id = ?, data_hora = ?, assunto = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, aula.getIdTurma());
            stmt.setTimestamp(2, Timestamp.valueOf(aula.getDataHora()));
            stmt.setString(3, aula.getAssunto());
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
        return aula;
    }

    public void deletarAula(int id) throws SQLException{
        String query = "DELETE FROM aula WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
