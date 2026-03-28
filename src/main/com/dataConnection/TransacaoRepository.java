package com.dataConnection;

import com.Model.Receita;
import com.Model.Despesa;
import com.Model.Transacao;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class TransacaoRepository extends Repository<Transacao> {

    // ===== CREATE =====

    @Override
    public void save(Transacao transacao) {
        if (transacao instanceof Receita) {
            salvarReceita((Receita) transacao);
        } else if (transacao instanceof Despesa) {
            salvarDespesa((Despesa) transacao);
        }
    }

    public void salvarReceita(Receita receita) {
        String sql = "INSERT INTO transacoes (descricao, valor, tipo, data) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, receita.getDescricao());
            stmt.setDouble(2, receita.getValor());
            stmt.setString(3, receita.getTipo());
            stmt.setDate(4, Date.valueOf(receita.getData()));

            stmt.executeUpdate();
            System.out.println("✓ Receita salva no banco de dados!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar receita: " + e.getMessage());
        }
    }

    public void salvarDespesa(Despesa despesa) {
        String sql = "INSERT INTO transacoes (descricao, valor, tipo, data) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, despesa.getDescricao());
            stmt.setDouble(2, despesa.getValor());
            stmt.setString(3, despesa.getTipo());
            stmt.setDate(4, Date.valueOf(despesa.getData()));

            stmt.executeUpdate();
            System.out.println("✓ Despesa salva no banco de dados!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar despesa: " + e.getMessage());
        }
    }

    // ===== READ =====

    @Override
    public Transacao findById(int id) {
        String sql = "SELECT id, descricao, valor, tipo, data FROM transacoes WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                String tipo = rs.getString("tipo");
                LocalDate data = rs.getDate("data").toLocalDate();

                if ("RECEITA".equals(tipo)) {
                    return new Receita(id, descricao, valor, data);
                } else if ("DESPESA".equals(tipo)) {
                    return new Despesa(id, descricao, valor, data);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar transação: " + e.getMessage());
        }

        return null;
    }

    public Transacao buscarPorId(int id) {
        return findById(id);
    }

    @Override
    public List<Transacao> findAll() {
        List<Transacao> transacoes = new ArrayList<>();
        String sql = "SELECT id, descricao, valor, tipo, data FROM transacoes ORDER BY data DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                String tipo = rs.getString("tipo");
                LocalDate data = rs.getDate("data").toLocalDate();

                if ("RECEITA".equals(tipo)) {
                    transacoes.add(new Receita(id, descricao, valor, data));
                } else if ("DESPESA".equals(tipo)) {
                    transacoes.add(new Despesa(id, descricao, valor, data));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar transações: " + e.getMessage());
        }

        return transacoes;
    }

    public List<Transacao> listarTodas() {
        return findAll();
    }

    public List<String> listarTodasFormatadas() {
        List<String> transacoes = new ArrayList<>();
        String sql = "SELECT id, descricao, valor, tipo, data FROM transacoes ORDER BY data DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String linha = String.format("[ID: %d] %s: R$ %.2f (%s) - %s",
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        rs.getString("tipo"),
                        rs.getString("data")
                );
                transacoes.add(linha);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar transações: " + e.getMessage());
        }

        return transacoes;
    }

    // ===== UPDATE =====

    @Override
    public void update(Transacao transacao) {
        String sql = "UPDATE transacoes SET descricao = ?, valor = ?, data = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transacao.getDescricao());
            stmt.setDouble(2, transacao.getValor());
            stmt.setDate(3, Date.valueOf(transacao.getData()));
            stmt.setInt(4, transacao.getId());

            int linhasAtualizadas = stmt.executeUpdate();

            if (linhasAtualizadas > 0) {
                System.out.println("✓ Transação atualizada com sucesso!");
            } else {
                System.out.println("✗ Nenhuma transação encontrada com esse ID!");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar transação: " + e.getMessage());
        }
    }

    public void atualizar(Transacao transacao) {
        update(transacao);
    }

    // ===== DELETE =====

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM transacoes WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasDeletadas = stmt.executeUpdate();

            if (linhasDeletadas > 0) {
                System.out.println("✓ Transação deletada com sucesso!");
            } else {
                System.out.println("✗ Nenhuma transação encontrada com esse ID!");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar transação: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        delete(id);
    }

    public void deletarTodas() {
        String sql = "DELETE FROM transacoes";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            int linhasDeletadas = stmt.executeUpdate(sql);
            System.out.println("✓ " + linhasDeletadas + " transação(ões) deletada(s)!");

        } catch (SQLException e) {
            System.err.println("Erro ao deletar transações: " + e.getMessage());
        }
    }

    // ===== CÁLCULOS =====

    public double calcularSaldo() {
        String sql = "SELECT SUM(CASE WHEN tipo = 'RECEITA' THEN valor ELSE -valor END) as saldo FROM transacoes";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                Object saldoObj = rs.getObject("saldo");
                if (saldoObj != null) {
                    return rs.getDouble("saldo");
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao calcular saldo: " + e.getMessage());
        }

        return 0.0;
    }

    public double calcularTotalReceitas() {
        String sql = "SELECT SUM(valor) as total FROM transacoes WHERE tipo = 'RECEITA'";
        return calcularTotal(sql);
    }

    public double calcularTotalDespesas() {
        String sql = "SELECT SUM(valor) as total FROM transacoes WHERE tipo = 'DESPESA'";
        return calcularTotal(sql);
    }

    private double calcularTotal(String sql) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                Object totalObj = rs.getObject("total");
                if (totalObj != null) {
                    return rs.getDouble("total");
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao calcular total: " + e.getMessage());
        }

        return 0.0;
    }
}