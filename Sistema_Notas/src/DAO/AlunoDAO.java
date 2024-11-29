package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoDAO {

    public boolean autenticarAluno(int matricula, String senha){
        // Não precisa mais instanciar ConexaoDAO
        Connection conn = null;
        try {
            conn = ConexaoDAO.getConexao(); // Conexão é obtida via Singleton
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        String sql = "SELECT * FROM aluno WHERE matricula_aluno = ? AND senha = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, matricula);
            pstmt.setString(2, senha);
            
            ResultSet rs = pstmt.executeQuery();
            
            return rs.next(); // Retorna true se encontrar o aluno, false caso contrário
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
