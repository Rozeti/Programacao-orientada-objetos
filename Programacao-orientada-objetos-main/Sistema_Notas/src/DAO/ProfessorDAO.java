package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessorDAO {

    // Método para autenticar o professor
    public boolean autenticarProfessor(int matricula, String senha) {
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        Connection conn = null;

        try {
            conn = conexaoDAO.getConexao(); // Obtém a conexão com o banco
            String sql = "SELECT * FROM professor WHERE matricula_professor = ? AND senha = ?";
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, matricula);
                pstmt.setString(2, senha);
                
                ResultSet rs = pstmt.executeQuery();
                
                // Retorna true se encontrar o professor, caso contrário false
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
