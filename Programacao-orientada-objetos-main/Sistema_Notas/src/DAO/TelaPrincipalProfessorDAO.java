package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TelaPrincipalProfessorDAO {

    public String getNomeCompletoPorMatricula(int matricula) {
        String nomeCompleto = "Professor"; // Valor padrão caso o nome não seja encontrado
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Obter conexão com o banco de dados
            conn = ConexaoDAO.getConexao();

            // Consulta para buscar o nome completo do professor
            String sql = "SELECT nome FROM professor WHERE matricula_professor = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, matricula);

            // Executar a consulta
            rs = stmt.executeQuery();
            if (rs.next()) {
                // Recupera o nome do professor
                nomeCompleto = rs.getString("nome");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar o nome completo do professor: " + e.getMessage());
        } finally {
            // Fechar recursos do banco de dados
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return nomeCompleto;
    }

    public String[] getNomesAlunosPorMatricula(int matricula) {
        List<String> nomesAlunos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Obter conexão com o banco de dados
            conn = ConexaoDAO.getConexao();

            // Consulta para buscar os nomes dos alunos associados à matrícula do professor
            String sql = "SELECT a.nome " +
                         "FROM aluno a " +
                         "JOIN matricula_disciplina md ON a.cod_aluno = md.aluno_cod_aluno " +
                         "JOIN disciplina d ON md.disciplina_codigo = d.codigo " +
                         "WHERE d.professor_cod_professor = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, matricula);

            // Executar a consulta
            rs = stmt.executeQuery();
            while (rs.next()) {
                // Recupera o nome do aluno
                nomesAlunos.add(rs.getString("nome"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar os nomes dos alunos: " + e.getMessage());
        } finally {
            // Fechar recursos do banco de dados
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        // Retorna os nomes dos alunos como um array
        return nomesAlunos.toArray(new String[0]);
    }
}
