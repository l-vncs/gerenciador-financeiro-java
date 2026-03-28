package com.dataConnection;

import java.sql.*;

public class DatabaseConnection {
    // Configurações do banco
    private static final String url = "jdbc:mysql://localhost:3306/financial_manager?useSSL=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "0000";  // Deixe vazio se não tem senha


    static {
        try {
            // Carrega o driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver MySQL carregado com sucesso!");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL não encontrado: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado ao banco de dados!");
            return conn;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
            throw e;
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexão fechada!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}