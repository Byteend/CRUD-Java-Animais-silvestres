package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnimalSilvestreDAO {

    private static final String URL = "jdbc:h2:mem:animais_db;DB_CLOSE_DELAY=-1;MODE=PostgreSQL";

    public AnimalSilvestreDAO() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver do H2 não encontrado.", e);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, "sa", "");
    }

    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS animal_silvestre ("
                + "nome VARCHAR(100) PRIMARY KEY, "
                + "peso_medio DOUBLE NOT NULL, "
                + "bioma VARCHAR(100) NOT NULL, "
                + "em_risco_extincao BOOLEAN NOT NULL)";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela de animais silvestres.", e);
        }
    }

    public void limparTabela() {
        String sql = "DELETE FROM animal_silvestre";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao limpar tabela.", e);
        }
    }

    public void salvar(AnimalSilvestre animal) {
        String sql = "INSERT INTO animal_silvestre (nome, peso_medio, bioma, em_risco_extincao) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, animal.getNome());
            stmt.setDouble(2, animal.getPesoMedio());
            stmt.setString(3, animal.getBioma());
            stmt.setBoolean(4, animal.isEmRiscoExtincao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar animal.", e);
        }
    }

    public List<AnimalSilvestre> listarTodos() {
        List<AnimalSilvestre> animais = new ArrayList<>();
        String sql = "SELECT nome, peso_medio, bioma, em_risco_extincao FROM animal_silvestre ORDER BY nome";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                AnimalSilvestre animal = new AnimalSilvestre();
                animal.setNome(rs.getString("nome"));
                animal.setPesoMedio(rs.getDouble("peso_medio"));
                animal.setBioma(rs.getString("bioma"));
                animal.setEmRiscoExtincao(rs.getBoolean("em_risco_extincao"));
                animais.add(animal);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar animais.", e);
        }

        return animais;
    }

    public AnimalSilvestre buscarPorNome(String nome) {
        String sql = "SELECT nome, peso_medio, bioma, em_risco_extincao FROM animal_silvestre WHERE nome = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                AnimalSilvestre animal = new AnimalSilvestre();
                animal.setNome(rs.getString("nome"));
                animal.setPesoMedio(rs.getDouble("peso_medio"));
                animal.setBioma(rs.getString("bioma"));
                animal.setEmRiscoExtincao(rs.getBoolean("em_risco_extincao"));
                return animal;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar animal por nome.", e);
        }

        return null;
    }

    public void atualizar(AnimalSilvestre animal) {
        String sql = "UPDATE animal_silvestre SET peso_medio = ?, bioma = ?, em_risco_extincao = ? WHERE nome = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, animal.getPesoMedio());
            stmt.setString(2, animal.getBioma());
            stmt.setBoolean(3, animal.isEmRiscoExtincao());
            stmt.setString(4, animal.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar animal.", e);
        }
    }

    public void excluir(String nome) {
        String sql = "DELETE FROM animal_silvestre WHERE nome = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir animal.", e);
        }
    }
}
