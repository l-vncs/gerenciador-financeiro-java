package com.Test;
import com.dataConnection.DatabaseConnection;
import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getConnection();

            if (conn != null) {
                System.out.println("✓ Conexão bem-sucedida!");

                // Testa uma query simples
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT VERSION()");

                if (rs.next()) {
                    System.out.println("MySQL Version: " + rs.getString(1));
                }

                rs.close();
                stmt.close();
                DatabaseConnection.closeConnection(conn);
            }
        } catch (SQLException e) {
            System.err.println("✗ Erro: " + e.getMessage());
        }
    }
}