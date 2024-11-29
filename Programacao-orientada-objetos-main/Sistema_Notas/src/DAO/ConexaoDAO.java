package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {

    private static final String URL = "jdbc:mysql://localhost:3307/escola"; // Substitua 'escola' pelo nome do seu banco de dados
    private static final String USUARIO = "root"; // Substitua pelo seu usuário do MySQL
    private static final String SENHA = "catolica"; // Substitua pela sua senha do MySQL

    // Instância única da classe ConexaoDAO
    private static Connection conn;

    // Método para obter a conexão com o banco de dados
    public static Connection getConexao() throws SQLException {
        if (conn == null || conn.isClosed()) {
            try {
                conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            } catch (SQLException e) {
                throw new SQLException("Erro ao conectar com o banco de dados: " + e.getMessage());
            }
        }
        return conn;
    }
}
