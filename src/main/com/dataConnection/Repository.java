package com.dataConnection;
import java.sql.*;
import java.util.List;

public abstract class Repository<T> {

    public abstract void save (T entity) throws SQLException;
    public abstract void update (T entity) throws SQLException;
    public abstract void delete (int id) throws SQLException;
    public abstract T findById (int id) throws SQLException;
    public abstract List<T> findAll() throws SQLException;

    protected void closeResources(java.sql.Connection conn, java.sql.Statement stmt, java.sql.ResultSet rs) {
        try{
            if (rs != null) rs.close();
            if (conn != null) conn.close();
            if (stmt != null) stmt.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
